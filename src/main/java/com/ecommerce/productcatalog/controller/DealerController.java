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
    public ResponseEntity<String> checkDealerByEmail(@PathVariable String email) {
        boolean exists = dealerService.findDealerByEmail(email);

        if (exists)
        {
            return ResponseEntity.ok(" Dealer found with email: " + email);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" No dealer found with email: " + email);
        }
    }

    @GetMapping("/available/dealers/{category}")
    public ResponseEntity<List<Dealer>> fetchDealersByProdCategory(@PathVariable String category)
    {
        return new ResponseEntity<>(dealerService.getDealersByProdCategory(category), HttpStatus.OK);
    }


}
