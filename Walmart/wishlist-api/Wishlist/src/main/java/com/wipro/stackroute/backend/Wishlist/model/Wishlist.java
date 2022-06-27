package com.wipro.stackroute.backend.Wishlist.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "wishlist")
public class Wishlist {

        @Id
        private  String id;
        private List<Items> wishlist;

        public Wishlist() {
        }

        public Wishlist(String id, List<Items> wishlist) {
            this.id = id;
            this.wishlist = wishlist;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<Items> getWishlist() {
            return wishlist;
        }

        public void setWishlist(List<Items> wishlist) {
            this.wishlist = wishlist;
        }

    @Override
    public String toString() {
        return "Wishlist{" +
                "id='" + id + '\'' +
                ", wishlist=" + wishlist +
                '}';
    }
}



