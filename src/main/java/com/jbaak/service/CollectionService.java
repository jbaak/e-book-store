package com.jbaak.service;

import com.jbaak.model.entity.Author;
import com.jbaak.model.entity.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CollectionService {
    Collection createCollection(Collection collection);
    List<Collection> getCollectionByUser(Integer userId);
    Collection getCollectionById(Integer collectionId);
    Collection updateCollection(Integer collectionId, Collection updateCollection);
    void deleteCollection(Integer collectionId);
}
