package com.jbaak.api;

import com.jbaak.model.entity.Book;
import com.jbaak.service.AdminBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin/books")
@RestController
@RequiredArgsConstructor
public class AdminBookController {

    private final AdminBookService adminBookService;

    @GetMapping
    public ResponseEntity<List<Book>> listAll() {
        List<Book> books = adminBookService.getAll();
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Book>> paginate(
            @PageableDefault(size=5, sort="firstName") Pageable pageable
    ) {
        Page<Book> books = adminBookService.paginate(pageable);
        return new ResponseEntity<Page<Book>>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable("id") Integer id) {
        Book book = adminBookService.findById(id);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book) {
        Book newBook = adminBookService.create(book);
        return new ResponseEntity<Book>(newBook, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable("id") Integer id, @RequestBody Book book) {
        Book updateBook = adminBookService.update(id, book);
        return new ResponseEntity<Book>(updateBook, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        adminBookService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
