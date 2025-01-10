package com.jbaak.service;

import com.jbaak.model.entity.Book;
import com.jbaak.model.entity.CollectionBook;

import java.util.List;

public interface CollectionBookService {

    CollectionBook addBookToCollection(Integer bookId, Integer collectionId);
    void  removeBookFromCollection(Integer bookId, Integer collectionId);
    List<Book> getBooksInCollection(Integer collectionId);
}
