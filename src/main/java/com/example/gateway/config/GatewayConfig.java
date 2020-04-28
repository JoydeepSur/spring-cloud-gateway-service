package com.example.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@EnableHystrix
@Configuration
public class GatewayConfig {
	
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()

                .route(p -> p
                        .path("/hello")
                        .filters(f ->
                                f.hystrix(config -> config.setName("hello-service")
                                                .setFallbackUri("forward:/hellofallback"))
                        )
                        .uri("http://localhost:8082")
                )
                .route(p -> p
                        .path("/facebook")
                        .filters(f ->
                                f.hystrix(config -> config.setName("facebook-service")
                                                .setFallbackUri("forward:/facebookfallback"))
                        )
                        .uri("http://localhost:8081")
                )
                .build();
	}

}
