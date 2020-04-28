package com.example.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

	@RequestMapping("/hellofallback")
	public Mono<String> countries() {
		return Mono.just("Hello API is taking too long to respond or is down. Please try again later");
	}

	@RequestMapping("/facebookfallback")
	public Mono<String> joke() {
		return Mono.just("Facebook API is taking too long to respond or is down. Please try again later");
	}

}
