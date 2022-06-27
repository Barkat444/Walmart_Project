package com.wipro.stackroute.backend.Wishlist.service;

import com.wipro.stackroute.backend.Wishlist.exception.*;
import com.wipro.stackroute.backend.Wishlist.model.Items;


import java.util.List;

public interface ItemService{
    boolean addItem(Items itm, String email)throws ItemAlreadyPresentInWishlistException, WishlistNotFoundWithThisEmailException;
    boolean deleteItem(String email,int product_id)throws ItemNotFoundException,WishlistNotFoundWithThisEmailException;

    boolean deleteAllItems(String email)throws NoItemPresentInWishlistException,WishlistNotFoundWithThisEmailException;

    List<Items> getAllItems(String email)throws NoItemPresentInWishlistException, WishlistNotFoundWithThisEmailException;

    boolean createNewWishlist(String email)throws WishlistAlreadyExistWithThisEmailException;
}
