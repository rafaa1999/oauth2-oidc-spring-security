package com.rafaa.book_service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.MAP;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookServiceApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void when_get_request_then_return_books(){
		webTestClient
				.get()
				.uri("/books")
				.exchange()
				.expectStatus().isOk();
	}

	@Test
	void when_get_a_specific_book_then_return_the_correct_one(){
		String expected_isbn = "1234567891";
		webTestClient
				.get()
				.uri("/books/{isbn}",expected_isbn)
				.exchange()
				.expectStatus().isOk()
				.expectBody(Book.class)
				.value(actualBook -> {
					assertThat(actualBook.isbn()).isEqualTo(expected_isbn);
				});
	}

	@Test
	void when_post_request_then_book_created(){
		var expectedBook = new Book("1231231231", "Title");
		webTestClient
				.post()
				.uri("/books")
				.bodyValue(expectedBook)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(Book.class).value(actualBook -> {
					assertThat(actualBook).isNotNull();
					assertThat(actualBook.isbn()).isEqualTo(expectedBook.isbn());
				});
	}

}
