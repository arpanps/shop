package com.shop.packages;

import java.util.List;

import com.shop.models.Package;

/**
 *
 * @author Arpan Khandelwal
 *
 */
public interface PackageService {

    Package getPackage(String packageId, String currency);

    List<Package> getPackages(String currency);

    Package createPackage(String name, String description, List<String> productIds, String currency);

    Package updatePackage(String packageId, String name, String description, List<String> productIds, String currency);

    void deletePackage(String packageId);

}