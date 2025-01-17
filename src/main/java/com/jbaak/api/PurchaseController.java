package com.jbaak.api;


import com.jbaak.model.entity.Purchase;
import com.jbaak.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<Purchase> createPurchase(@RequestBody Purchase purchase) {
        Purchase savedPurchase = purchaseService.createPurchase(purchase);
        return new ResponseEntity<>(savedPurchase, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Purchase>> getPurchaseHistoryByUserId(@PathVariable Integer userId) {
        List<Purchase> purchaseHistory = purchaseService.getPurchaseHistoryByUserId(userId);
        return ResponseEntity.ok(purchaseHistory);
    }
}
