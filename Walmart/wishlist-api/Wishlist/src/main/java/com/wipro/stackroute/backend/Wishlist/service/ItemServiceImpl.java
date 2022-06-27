package com.wipro.stackroute.backend.Wishlist.service;

import com.wipro.stackroute.backend.Wishlist.exception.*;
import com.wipro.stackroute.backend.Wishlist.model.Items;
import com.wipro.stackroute.backend.Wishlist.model.Wishlist;
import com.wipro.stackroute.backend.Wishlist.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    ItemsRepository itemsRepository;


    //Create a new empty wishlist for new user
    @Override
    public boolean createNewWishlist(String email)throws WishlistAlreadyExistWithThisEmailException {
        if(itemsRepository.existsById(email)){
           throw new WishlistAlreadyExistWithThisEmailException("Wishlist Already Exist With This Email!!");
        }
        else{
            Wishlist wishlist=new Wishlist();
            wishlist.setId(email);
            wishlist.setWishlist(new ArrayList<>());
            itemsRepository.insert(wishlist);
        return true;
        }
    }

    //Add new item into wishlist
    @Override
    public boolean addItem(Items itm, String email)throws ItemAlreadyPresentInWishlistException, WishlistNotFoundWithThisEmailException {
      if(itemsRepository.existsById(email)){
          List<Items>list;
          Wishlist wishlist=itemsRepository.findById(email).get();
            if(wishlist.getWishlist().size()==0){
                list=new ArrayList<>();
                list.add(itm);
            }else {
               list=wishlist.getWishlist();

               for(Items product:list){
                   if(product.getProductId()==itm.getProductId()){
                       throw new ItemAlreadyPresentInWishlistException("Item Already Present In Wishlist Exception");
                   }
               }
                 list.add(itm);
            }
            wishlist.setWishlist(list);
          itemsRepository.save(wishlist);
        return true;
      }
      else
        throw new WishlistNotFoundWithThisEmailException("Wishlist with this email account not found");
    }


    //Delete all items in wishlist
    @Override
    public boolean deleteAllItems(String email)throws NoItemPresentInWishlistException,WishlistNotFoundWithThisEmailException{
        if(itemsRepository.existsById(email)){
            Wishlist wishlist=itemsRepository.findById(email).get();
         if(wishlist.getWishlist().size()>0){
            wishlist.setWishlist(new ArrayList<>());
            itemsRepository.save(wishlist);
            return true;
         }
         else
             throw new NoItemPresentInWishlistException("No item Present In Wishlist");
        }
        else
            throw new WishlistNotFoundWithThisEmailException("Wishlist with this email account not found");
    }

    //Delete a single item in wishlist
    @Override
    public boolean deleteItem(String email,int product_id)throws ItemNotFoundException,WishlistNotFoundWithThisEmailException {
        if(itemsRepository.existsById(email)){
            int flag=0;
        Wishlist wishlist=itemsRepository.findById(email).get();
          List<Items> list=wishlist.getWishlist();
            for(Items item :list){
                if(item.getProductId()==product_id) {
                    list.remove(item);
                    flag=1;
                    break;
                }
            }
            if(flag==1) {
                wishlist.setWishlist(list);
                itemsRepository.save(wishlist);
                return true;
            }
           else
               throw new ItemNotFoundException("Item Not Found");
        }
        else
            throw new WishlistNotFoundWithThisEmailException("Wishlist with this email account not found");
    }



    //Get all items in from wishlist
    @Override
    public List<Items> getAllItems(String email)throws NoItemPresentInWishlistException,WishlistNotFoundWithThisEmailException {
        if(itemsRepository.existsById(email)){
           Wishlist wish=itemsRepository.findById(email).get();
           if(wish.getWishlist().size()>0)
              return wish.getWishlist();
           else
               throw new NoItemPresentInWishlistException("No Item Present In Wishlist");
        }
        else
            throw new WishlistNotFoundWithThisEmailException("Wishlist with this email account not found");
    }

}



