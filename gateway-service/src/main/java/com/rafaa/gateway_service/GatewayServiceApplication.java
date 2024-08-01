package com.rafaa.gateway_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class);
	}

}

@RestController
class WelcomeController{

	record Welcome(String message){}

	@GetMapping("/welcome")
	Mono<Welcome> getWelcome(){
		return Mono.just(new Welcome("welcome to gateway-service"));
	}

}