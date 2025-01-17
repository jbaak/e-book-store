package com.jbaak.service;

import com.jbaak.model.entity.Purchase;

import java.util.List;

public interface PurchaseService {

    Purchase createPurchase(Purchase purchase);
    List<Purchase> getPurchaseHistoryByUserId(Integer userId);
}
