package com.shop.rest;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.shop.config.Constants;
import com.shop.models.Package;
import com.shop.models.PackageRequest;
import com.shop.packages.PackageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 *
 * @author Arpan Khandelwal
 *
 */
@RestController
@RequestMapping("api/v1/")
@Tag(name = "Package", description = "REST APIs for package")
public class PackageController {

    private static final String DEFAULT_CURRENCY_CODE = Constants.DEFAULT_CURRENCY;

    @Autowired
    private PackageService packageService;

    // TODO : Current API is not scalable. Need a criteria based API for pagination.
    @Operation(summary = "Get packages", description = "Api to get all package list.")
    @RequestMapping(value = "/packages", method = RequestMethod.GET)
    public ResponseEntity<List<Package>> getPackages(
            @RequestParam(value = "currencyCode", required = false, defaultValue = DEFAULT_CURRENCY_CODE) final String currencyCode) {
        return ResponseEntity.ok(packageService.getPackages(currencyCode));
    }

    @Operation(summary = "Get package", description = "Api to get package info by id")
    @RequestMapping(value = "/packages/{packageId}", method = RequestMethod.GET)
    public ResponseEntity<Package> getPackage(@PathVariable("packageId") final String packageId,
            @RequestParam(value = "currencyCode", required = false, defaultValue = DEFAULT_CURRENCY_CODE) final String currencyCode) {
        return ResponseEntity.ok(packageService.getPackage(packageId, currencyCode));
    }

    @Operation(summary = "Add package", description = "Api to add a new package")
    @RequestMapping(value = "/packages", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Package> savePackage(@RequestBody final PackageRequest packageRequest,
            @RequestParam(value = "currencyCode", required = false, defaultValue = DEFAULT_CURRENCY_CODE) final String currencyCode) {
        final Package shopPackage = packageService.createPackage(packageRequest.getName(),
                packageRequest.getDescription(), packageRequest.getProductIds(), currencyCode);
        final URI resourceLocation = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(shopPackage.getId()).toUri();
        return ResponseEntity.created(resourceLocation).body(shopPackage);
    }

    @Operation(summary = "Update package", description = "Api to update package ")
    @RequestMapping(value = "/packages/{packageId}", method = RequestMethod.PUT)
    public ResponseEntity<Package> updatePackage(@PathVariable("packageId") final String packageId,
            @RequestBody final PackageRequest packageRequest,
            @RequestParam(value = "currencyCode", required = false, defaultValue = DEFAULT_CURRENCY_CODE) final String currencyCode) {
        final Package shopPackage = packageService.updatePackage(packageId, packageRequest.getName(),
                packageRequest.getDescription(), packageRequest.getProductIds(), currencyCode);
        return ResponseEntity.ok(shopPackage);
    }

    @Operation(summary = "Delete package", description = "Api to delete package ")
    @RequestMapping(value = "/packages/{packageId}", method = RequestMethod.DELETE)
    public ResponseEntity<Package> getPackage(@PathVariable("packageId") final String packageId) {
        packageService.deletePackage(packageId);
        return ResponseEntity.ok().build();
    }
}