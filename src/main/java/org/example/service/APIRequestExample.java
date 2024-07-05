package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.CatFact;
import org.example.dto.Employee;
import org.example.dto.EmployeeListResponse;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
public class APIRequestExample {
    private final HttpClient httpClient;
    private final ObjectMapper mapper;
    public APIRequestExample(
            HttpClient httpClient, ObjectMapper mapper
    ) {
        this.httpClient = httpClient;
        this.mapper = mapper;
    }
    public List<Employee> getAllEmployee() throws URISyntaxException, ExecutionException, InterruptedException {
        HttpRequest employeeRequest = HttpRequest.newBuilder()
                .uri(new URI("https://dummy.restapiexample.com/api/v1/employees"))
                .GET()
                .build();
        CompletableFuture<HttpResponse<String>> employeeListResponse = httpClient.sendAsync(employeeRequest, HttpResponse.BodyHandlers.ofString());
        CompletableFuture<EmployeeListResponse> employeeListFuture = employeeListResponse.thenApply(stringHttpResponse -> {
            try {
                return mapper.readValue(stringHttpResponse.body(), new TypeReference<EmployeeListResponse>() {});
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

        HttpRequest catFactRequest = HttpRequest.newBuilder()
                .uri(new URI("https://catfact.ninja/fact"))
                .GET()
                .build();
        CompletableFuture<HttpResponse<String>> catFactResponse = httpClient.sendAsync(catFactRequest, HttpResponse.BodyHandlers.ofString());
        CompletableFuture<CatFact> catFactFuture = catFactResponse.thenApply(stringHttpResponse -> {
            try {
                return mapper.readValue(stringHttpResponse.body(), CatFact.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

        log.info(String.valueOf(catFactFuture.get()));
        List<Employee> employeeList = employeeListFuture.get().getEmployeeList();
        log.info(employeeList.toString());
        return employeeList;
    }
}
