package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.service.APIRequestExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
@Slf4j
public class CompletableFutureExample implements CommandLineRunner {
    @Autowired
    APIRequestExample apiRequestExample;
    public static void main(String[] args) {
        SpringApplication.run(CompletableFutureExample.class, args);
    }

    @Override
    public void run(String... args) throws URISyntaxException, ExecutionException, InterruptedException {
        apiRequestExample.getAllEmployee();
    }
}
