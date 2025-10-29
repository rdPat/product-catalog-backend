package com.ecommerce.productcatalog.service.impl;

import com.ecommerce.productcatalog.entity.Dealer;
import com.ecommerce.productcatalog.repository.DealerRepo;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DealerService
{
    @Autowired
    private DealerRepo dealerRepo;

    public List<Dealer> fetchDealers()
    {
        return dealerRepo.findAll();
    }

    public Dealer addDealer(Dealer dealer)
    {
        System.out.println("received--------"+dealer.toString());
        return dealerRepo.save(dealer);
    }

   public boolean findDealerByEmail(String email)
   {

       return dealerRepo.findByEmail(email).isPresent();
   }


}
