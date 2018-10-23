package com.aaxis.microservice.training.demo1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.aaxis.microservice.training.demo1.dao.ItemPriceDAO;
import com.aaxis.microservice.training.demo1.domain.ItemPrice;

@Component
public class ItemPriceService {

    @Autowired
    private ItemPriceDAO mItemPriceDAO;
    
    @Cacheable(value="itemPrice", key="#pProductId")
    public ItemPrice findItemPriceById(String pProductId){
        Optional<ItemPrice> optionalItemPrice = mItemPriceDAO.findById(pProductId);
        if(optionalItemPrice.isPresent()){
            return optionalItemPrice.get();
        }
        return null;
    }

    public void initData(){
        mItemPriceDAO.insertItemPrice();
    }

}
