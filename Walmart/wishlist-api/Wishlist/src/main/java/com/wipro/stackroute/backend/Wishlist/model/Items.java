package com.wipro.stackroute.backend.Wishlist.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "item")
public class Items {
    @Id
    private int productId;
    private  String title;
    private String image;
    private int rating;
    private int ratingTotal;
    private int shippingDays;
    private double price;



    public Items() {
    }
    	public Items(int productId, String title, String image, int rating, int ratingTotal, int shippingDays,
				 double price) {
		this.productId = productId;
    	this.title = title;
		this.image = image;
		this.rating = rating;
		this.ratingTotal = ratingTotal;
		this.shippingDays = shippingDays;
		this.price = price;
	}
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }



	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getRatingTotal() {
		return ratingTotal;
	}
	public void setRatingTotal(int ratingTotal) {
		this.ratingTotal = ratingTotal;
	}
	public int getShippingDays() {
		return shippingDays;
	}
	public void setShippingDays(int shippingDays) {
		this.shippingDays = shippingDays;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "items [productId=" + productId + ", title=" + title + ", image=" + image + ", rating=" + rating
				+ ", ratingTotal=" + ratingTotal + ", shippingDays=" + shippingDays + ", price=" + price + "]";
	}


}
