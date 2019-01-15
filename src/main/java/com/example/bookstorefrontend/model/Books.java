package com.example.bookstorefrontend.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Books {
    private final List<Book> books;
    private final Integer numberOfBooks;

    @JsonCreator
    public Books(@JsonProperty("books") List<Book> books,
                 @JsonProperty("numberOfBooks") Integer numberOfBooks) {
        this.books = books;
        this.numberOfBooks = numberOfBooks;
    }

    public List<Book> getBooks() {
        return books;
    }

    public Integer getNumberOfBooks() {
        return numberOfBooks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Books books1 = (Books) o;
        return Objects.equals(books, books1.books) &&
                Objects.equals(numberOfBooks, books1.numberOfBooks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(books, numberOfBooks);
    }

    @Override
    public String toString() {
        return "Books{" +
                "books=" + books +
                ", numberOfBooks=" + numberOfBooks +
                '}';
    }
}
