package com.example.bookstorefrontend.service;

import com.example.bookstorefrontend.model.Book;
import com.example.bookstorefrontend.model.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

@Service
public class BookApiClientImpl implements BookApiClient {

    private final RestOperations restTemplate;
    private final String url;

    @Autowired
    public BookApiClientImpl(RestOperations restTemplate, @Value("${bookapi.url}") String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    @Override
    public Books fetchAllBooks() {
        return restTemplate.getForObject(url, Books.class);
    }

    @Override
    public Book fetchBookInfo(String bookId) {
        Book book = restTemplate.getForObject(url + "/" + bookId + "/details", Book.class);
        return new Book(bookId, book.getTitle(), book.getAuthor(), book.getDescription());
    }
}
