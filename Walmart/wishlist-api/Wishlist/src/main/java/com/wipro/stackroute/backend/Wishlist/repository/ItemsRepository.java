package com.wipro.stackroute.backend.Wishlist.repository;

import com.wipro.stackroute.backend.Wishlist.model.Items;
import com.wipro.stackroute.backend.Wishlist.model.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends MongoRepository<Wishlist,String>{
}
