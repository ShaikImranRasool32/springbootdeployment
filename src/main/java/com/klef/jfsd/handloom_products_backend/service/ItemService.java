//package com.klef.jfsd.handloom_products_backend.service;
//
//import com.klef.jfsd.handloom_products_backend.model.Item;
//import com.klef.jfsd.handloom_products_backend.model.Seller;
//import com.klef.jfsd.handloom_products_backend.repository.ItemRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ItemService {
//
//    @Autowired
//    private ItemRepository itemRepository;
//
//    public Item addItem(Item item) {
//        return itemRepository.save(item);
//    }
//
//    public List<Item> getItemsBySellerEmail(String sellerEmail) {
//        return itemRepository.findBySeller_SellerEmail(sellerEmail);
//    }
//
//    public Item updateItem(Long itemId, Item item) {
//        Item existingItem = itemRepository.findById(itemId).orElseThrow();
//        existingItem.setItemName(item.getItemName());
//        existingItem.setItemPrice(item.getItemPrice());
//        existingItem.setItemDescription(item.getItemDescription());
//        return itemRepository.save(existingItem);
//    }
//
//    public void deleteItem(Long itemId) {
//        itemRepository.deleteById(itemId);
//    }
//}
