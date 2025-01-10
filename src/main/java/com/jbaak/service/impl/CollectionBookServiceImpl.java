package com.jbaak.service.impl;

import com.jbaak.model.entity.Book;
import com.jbaak.model.entity.Collection;
import com.jbaak.model.entity.CollectionBook;
import com.jbaak.repository.CollectionBookRepository;
import com.jbaak.repository.CollectionRepository;
import com.jbaak.service.CollectionBookService;
import com.jbaak.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectionBookServiceImpl implements CollectionBookService {

    private final CollectionBookRepository collectionBookRepository;
    private final CollectionRepository collectionRepository;

    @Override
    public CollectionBook addBookToCollection(Integer bookId, Integer collectionId) {
        LocalDateTime addedDate = LocalDateTime.now();
        collectionBookRepository.addBookToCollection(bookId, collectionId, addedDate);

        CollectionBook collectionBook = new CollectionBook();
        collectionBook.setBook(bookId);
        collectionBook.setCollection(collectionId);
        collectionBook.setAddedDate(addedDate);

        return collectionBook;
    }

    @Override
    public void removeBookFromCollection(Integer bookId, Integer collectionId) {
        collectionBookRepository.deleteByBookAndCollection(bookId, collectionId);
    }

    @Override
    public List<Book> getBooksInCollection(Integer collectionId) {
        Collection collection  = collectionRepository.findById(collectionId)
                .orElseThrow(() -> new RuntimeException("Collection not found"));
        return collectionBookRepository.findBooksByCollection(collection);

    }
}
