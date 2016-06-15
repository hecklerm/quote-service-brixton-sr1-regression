package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding(Sink.class)
public class QuoteServiceApplication {
    @Bean
    @Autowired
    CommandLineRunner commandLineRunner(QuoteRepository quoteRepository) {
        return args -> {
            quoteRepository.save(new Quote("Go Cards!", "Everyone in STL"));
            quoteRepository.save(new Quote("Go Cubs!", "No one in STL"));
            quoteRepository.save(new Quote("Go (away) Cubs!", "Everyone in STL"));

            quoteRepository.findAll().forEach(System.out::println);
        };
    }

	public static void main(String[] args) {
		SpringApplication.run(QuoteServiceApplication.class, args);
	}
}
