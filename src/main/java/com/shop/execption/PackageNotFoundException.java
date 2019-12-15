package com.shop.execption;

/**
 *
 * @author Arpan Khandelwal
 *
 */
public class PackageNotFoundException extends EntityNotFoundException {
    public PackageNotFoundException(final String errorMessage) {
        super(errorMessage);
    }
}