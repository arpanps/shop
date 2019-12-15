package com.shop.execption;

/**
 *
 * @author Arpan Khandelwal
 *
 */
public class ProductNotFoundException extends EntityNotFoundException {
    public ProductNotFoundException(final String errorMessage) {
        super(errorMessage);
    }
}