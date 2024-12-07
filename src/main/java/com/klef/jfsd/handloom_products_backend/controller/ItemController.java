//package com.klef.jfsd.handloom_products_backend.controller;
//
//import com.klef.jfsd.handloom_products_backend.model.Item;
//import com.klef.jfsd.handloom_products_backend.service.ItemService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/items")
//public class ItemController {
//
//    @Autowired
//    private ItemService itemService;
//
//    @PostMapping("/add")
//    public ResponseEntity<Item> addItem(@RequestBody Item item) {
//        Item createdItem = itemService.addItem(item);
//        return ResponseEntity.ok(createdItem);
//    }
//
//    @GetMapping("/seller/{sellerEmail}")
//    public List<Item> getItemsBySeller(@PathVariable String sellerEmail) {
//        return itemService.getItemsBySellerEmail(sellerEmail);
//    }
//
//    @PutMapping("/{itemId}")
//    public ResponseEntity<Item> updateItem(@PathVariable Long itemId, @RequestBody Item item) {
//        Item updatedItem = itemService.updateItem(itemId, item);
//        return ResponseEntity.ok(updatedItem);
//    }
//
//    @DeleteMapping("/{itemId}")
//    public ResponseEntity<Void> deleteItem(@PathVariable Long itemId) {
//        itemService.deleteItem(itemId);
//        return ResponseEntity.noContent().build();
//    }
//}
