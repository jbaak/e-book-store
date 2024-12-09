package com.jbaak.service.impl;

import com.jbaak.model.entity.Purchase;
import com.jbaak.model.entity.PurchaseItem;
import com.jbaak.model.enums.PaymentStatus;
import com.jbaak.repository.PurchaseRepository;
import com.jbaak.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;

    @Transactional
    @Override
    public Purchase createPurchase(Purchase purchase) {

        purchase.setCreatedAt(LocalDateTime.now());

        purchase.setPaymentStatus(PaymentStatus.PENDING);

        Float total = purchase.getItems()
        .stream()
        .map(item -> item.getPrice() * item.getQuantity() )
                .reduce((float) 0, Float::sum);
        purchase.setTotal(total);

        purchase.getItems().forEach(item->item.setPurchase(purchase));

        return purchaseRepository.save(purchase);
    }

    @Override
    public List<Purchase> getPurchaseHistoryByUserId(Integer userId) {
        return List.of();
    }
}
