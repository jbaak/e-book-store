package com.jbaak.service.impl;

import com.jbaak.model.entity.Author;
import com.jbaak.model.entity.Book;
import com.jbaak.model.entity.Category;
import com.jbaak.repository.AuthorRepository;
import com.jbaak.repository.BookRepository;
import com.jbaak.repository.CategoryRepository;
import com.jbaak.service.AdminBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminBookServiceImpl implements AdminBookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Book> paginate(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Book findById(Integer id) {
        return bookRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Book not found whit id : " + id));
    }

    @Transactional
    @Override
    public Book create(Book book) {
        Category category = categoryRepository.findById(book.getCategory().getId())
                        .orElseThrow( () -> new RuntimeException("Category not found whit id: " + book.getCategory().getId()));
        Author author  = authorRepository.findById(book.getAuthor().getId())
                .orElseThrow( () -> new RuntimeException("Author not found whit id: " + book.getAuthor().getId()));

        book.setCategory(category);
        book.setAuthor(author);
        book.setCreatedAt(LocalDateTime.now());

        return bookRepository.save(book);
    }

    @Transactional
    @Override
    public Book update(Integer id, Book updateBook) {
        Book bookFromDb = findById(id);

        Category category = categoryRepository.findById(updateBook.getCategory().getId())
                .orElseThrow( () -> new RuntimeException("Category not found whit id: " + updateBook.getCategory().getId()));
        Author author  = authorRepository.findById(updateBook.getAuthor().getId())
                .orElseThrow( () -> new RuntimeException("Author not found whit id: " + updateBook.getAuthor().getId()));


        bookFromDb.setTitle(updateBook.getTitle());
        bookFromDb.setAuthor(author);
        bookFromDb.setCategory(category);
        bookFromDb.setUpdatedAt(LocalDateTime.now());
        bookFromDb.setDescription(updateBook.getDescription());
        bookFromDb.setPrice(updateBook.getPrice());
        bookFromDb.setSlug(updateBook.getSlug());
        bookFromDb.setCoverPath(updateBook.getCoverPath());
        bookFromDb.setFilePath(updateBook.getFilePath());
        return bookRepository.save(bookFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Book book = findById(id);
        bookRepository.delete(book);
    }
}
