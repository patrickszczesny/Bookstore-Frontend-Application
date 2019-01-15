package com.example.bookstorefrontend.service;

import com.example.bookstorefrontend.model.Book;
import com.example.bookstorefrontend.model.Books;

public interface BookApiClient {
    Books fetchAllBooks();

    Book fetchBookInfo(String bookId);
}
