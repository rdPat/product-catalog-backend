package com.ecommerce.productcatalog.controller;

import com.ecommerce.productcatalog.entity.Dealer;
import com.ecommerce.productcatalog.service.impl.DealerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DealerController
{
    @Autowired
    DealerService dealerService;

    @GetMapping("/fetch/dealers")
    public ResponseEntity<List<Dealer>> getAllDealers()
    {
        return new ResponseEntity<>(dealerService.fetchDealers(), HttpStatus.OK);
    }

    @PostMapping("/add/dealer")
    public ResponseEntity<Dealer> createDealer(@Valid @RequestBody Dealer dealer)
    {
        return new ResponseEntity<>(dealerService.addDealer(dealer),HttpStatus.CREATED);
    }
    @GetMapping("/check/email/{email}")
    public void checkDealerByEmail(@PathVariable String email) {
        boolean exists = dealerService.findDealerByEmail(email);

        if (exists) {
            System.out.println(" Dealer found with email: " + email);
        } else {
            System.out.println(" No dealer found with email: " + email);
        }
    }
}
