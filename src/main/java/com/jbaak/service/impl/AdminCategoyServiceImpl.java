package com.jbaak.service.impl;

import com.jbaak.model.entity.Category;
import com.jbaak.repository.CategoryRepository;
import com.jbaak.service.AdminCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminCategoyServiceImpl implements AdminCategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Category> paginate(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Category findById(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Category not found"));
    }

    @Override
    public Category create(Category category) {
        category.setCreatedAt(LocalDateTime.now());
        return categoryRepository.save(category);
    }

    @Transactional
    @Override
    public Category update(Integer id, Category updateCategory) {
        Category categoryFromDb = findById(id);
        categoryFromDb.setName(updateCategory.getName());
        categoryFromDb.setDescription(updateCategory.getDescription());
        categoryFromDb.setUpdatedAt(LocalDateTime.now());
        return categoryRepository.save(categoryFromDb);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        Category categoryFromDb = findById(id);
        categoryRepository.delete(categoryFromDb);
    }
}
