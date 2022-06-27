package com.wipro.stackroute.backend.Wishlist.exception;

import com.wipro.stackroute.backend.Wishlist.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ItemExceptionHandler {

    @ExceptionHandler(ItemAlreadyPresentInWishlistException.class)
    public ResponseEntity<ErrorResponse> ItemAlreadyPresentInWishlistHandler(ItemAlreadyPresentInWishlistException exception){
        ErrorResponse errorResponse=new ErrorResponse(409,false,exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoItemPresentInWishlistException.class)
    public ResponseEntity<ErrorResponse> NoItemPresentInWishlistHandler(NoItemPresentInWishlistException exception){
        ErrorResponse errorResponse=new ErrorResponse(400,false,exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WishlistAlreadyExistWithThisEmailException.class)
    public ResponseEntity<ErrorResponse> WishlistAlreadyExistWithThisEmailHandler(WishlistAlreadyExistWithThisEmailException exception){
        ErrorResponse errorResponse=new ErrorResponse(409,false,exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(WishlistNotFoundWithThisEmailException.class)
    public ResponseEntity<ErrorResponse> WishlistNotFoundWithThisEmailHandler(WishlistNotFoundWithThisEmailException exception){
        ErrorResponse errorResponse=new ErrorResponse(400,false,exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ErrorResponse> ItemNotFoundExceptionHandler(ItemNotFoundException exception){
        ErrorResponse errorResponse=new ErrorResponse(404,false,exception.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


}

