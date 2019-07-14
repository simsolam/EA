package edu.mum.cs544;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Client implements CommandLineRunner {
    @Autowired
    private RestTemplate restTemplate;

    private final String bookUrl = "http://localhost:8080/api/books/{id}";
    private final String booksUrl = "http://localhost:8080/api/books";

    public void run(String... args) {
        ResponseEntity<List<Book>> response = restTemplate.exchange(booksUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Book>>() {
                });
        System.out.println(response.getBody());

        Book b = new Book();
        b.setAuthor("Test Author");
        b.setISBN("978-0-306-40615-7");
        b.setPrice(2);
        b.setTitle("Test Title");
        restTemplate.postForLocation(booksUrl, b);

        restTemplate.delete(bookUrl, 1);

        b.setAuthor("updated");
        restTemplate.put(bookUrl, b, 2);

        response = restTemplate.exchange(booksUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Book>>() {
        });
        System.out.println(response.getBody());

        System.out.println(restTemplate.getForObject(bookUrl, Book.class, 3));
    }
}