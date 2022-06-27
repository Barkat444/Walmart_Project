package com.wipro.stackroute.backend.Wishlist.controller;

import com.wipro.stackroute.backend.Wishlist.exception.*;
import com.wipro.stackroute.backend.Wishlist.model.Items;
import com.wipro.stackroute.backend.Wishlist.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class ItemsController {

    @Autowired
    ItemService itemService;


    @GetMapping("/wishlist/create/{id}")
    public ResponseEntity<String> createNewWishlist(@PathVariable("id")String email)throws WishlistAlreadyExistWithThisEmailException {
        if(itemService.createNewWishlist(email))
            return ResponseEntity.ok("New Wishlist created");
        else
            return new ResponseEntity<>(
                    "Error in creating Wishlist",
                    HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/wishlist/{id}")
    public ResponseEntity<List<Items>> getAllItems(@PathVariable("id")String email)throws NoItemPresentInWishlistException,WishlistNotFoundWithThisEmailException{
        List<Items> items =itemService.getAllItems(email);
        return ResponseEntity.ok(items);
    }

    @PostMapping("/wishlist/{id}")
    public ResponseEntity<Items> AddItems(@PathVariable("id")String email,@RequestBody Items item)throws ItemAlreadyPresentInWishlistException, WishlistNotFoundWithThisEmailException{
        System.out.println("Controller "+item+"   " +email);
        boolean flag=itemService.addItem(item,email);
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/wishlist/{id}/{product_id}")
    public ResponseEntity<String> deleteItems(@PathVariable("id")String email,@PathVariable("product_id") int product_id)throws ItemNotFoundException,WishlistNotFoundWithThisEmailException{
        boolean flag=itemService.deleteItem(email,product_id);
        if(flag==true)
             return new ResponseEntity<>("Product Deleted",HttpStatus.OK);
        else
            return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
    }



    @DeleteMapping("/wishlist/{id}/clearAll")
    public ResponseEntity<String> deleteAllItems(@PathVariable("id")String email)throws NoItemPresentInWishlistException,WishlistNotFoundWithThisEmailException{
        boolean flag=itemService.deleteAllItems(email);
        if(flag==true)
            return new ResponseEntity<>("All Product Deleted",HttpStatus.OK);
        else
            return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
    }
}
