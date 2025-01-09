package com.jbaak.service.impl;

import com.jbaak.model.entity.Collection;
import com.jbaak.repository.CollectionRepository;
import com.jbaak.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepository collectionRepository;

    @Transactional
    @Override
    public Collection createCollection(Collection collection) {
        collection.setCreatedAt(LocalDateTime.now());
        return collectionRepository.save(collection);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Collection> getCollectionByUser(Integer userId) {
        return collectionRepository.findByCustomerId(userId);
    }

    @Transactional
    @Override
    public Collection getCollectionById(Integer collectionId) {
        return collectionRepository.findById(collectionId).orElseThrow(() -> new RuntimeException("Collection not found"));
    }

    @Transactional
    @Override
    public Collection updateCollection(Integer collectionId, Collection updateCollection) {
       Collection existingCollection = getCollectionById(collectionId);
       existingCollection.setName(updateCollection.getName());
       existingCollection.setUpdatedAt(LocalDateTime.now());
       return collectionRepository.save(existingCollection);
    }

    @Transactional
    @Override
    public void deleteCollection(Integer collectionId) {
        Collection collection = getCollectionById(collectionId);
        collectionRepository.delete(collection);
    }
}
