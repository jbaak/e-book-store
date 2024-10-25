package com.jbaak.api;

import com.jbaak.model.entity.Author;
import com.jbaak.model.entity.Category;
import com.jbaak.service.AdminAuthorService;
import com.jbaak.service.AdminCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin/authors")
@RestController
@RequiredArgsConstructor
public class AdminAuthorController {

    private final AdminAuthorService adminAuthorService;

    @GetMapping
    public ResponseEntity<List<Author>> listAll() {
        List<Author> authors = adminAuthorService.getAll();
        return new ResponseEntity<List<Author>>(authors, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Author>> paginate(
            @PageableDefault(size=5, sort="firstName") Pageable pageable
    ) {
        Page<Author> authors = adminAuthorService.paginate(pageable);
        return new ResponseEntity<Page<Author>>(authors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getById(@PathVariable("id") Integer id) {
        Author author = adminAuthorService.findById(id);
        return new ResponseEntity<Author>(author, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Author> create(@RequestBody Author author) {
        Author newAuthor = adminAuthorService.create(author);
        return new ResponseEntity<Author>(newAuthor, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@PathVariable("id") Integer id, @RequestBody Author author) {
        Author updateAuthor = adminAuthorService.update(id, author);
        return new ResponseEntity<Author>(updateAuthor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        adminAuthorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
