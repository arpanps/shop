package com.shop.packages;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.execption.PackageNotFoundException;
import com.shop.execption.ProductNotFoundException;
import com.shop.models.Package;
import com.shop.models.Product;
import com.shop.persistence.packages.PackageDto;
import com.shop.persistence.packages.PackageRepository;
import com.shop.product.ProductService;

/**
 *
 * @author Arpan Khandelwal
 *
 */
@Service
@Transactional
public class PackageServiceImpl implements PackageService {

    @Autowired
    private ProductService productService;

    @Autowired
    private PackageRepository packageRepository;

    private PackageDto checkAndGet(final String packageId) {
        final Optional<PackageDto> packageDto = packageRepository.findById(packageId);
        if (packageDto.isPresent()) {
            return packageDto.get();
        }

        throw new PackageNotFoundException(String.format("Package %d not found", packageId));
    }

    private void validateProductIds(final List<String> productIds) {
        if (productIds == null || productIds.isEmpty()) {
            throw new IllegalArgumentException("Products can not be empty");
        }
        try {
            // Validate products
            productService.validate(productIds);
        } catch (final ProductNotFoundException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public Package getPackage(final String packageId, final String currency) {
        return preparePackage(checkAndGet(packageId), currency);
    }

    @Override
    public List<Package> getPackages(final String currency) {
        return StreamSupport.stream(packageRepository.findAll().spliterator(), false)
                .map(packageDto -> preparePackage(packageDto, currency)).collect(Collectors.toList());
    }

    @Override
    public Package createPackage(final String name, final String description, final List<String> productIds,
            final String currency) {
        validateProductIds(productIds);

        // Create new unique id for the package
        final String id = UUID.randomUUID().toString();
        final PackageDto packageDto = packageRepository.save(new PackageDto(id, name, description, productIds));
        return preparePackage(packageDto, currency);
    }

    @Override
    public Package updatePackage(final String packageId, final String name, final String description,
            final List<String> productIds, final String currency) {
        validateProductIds(productIds);

        final PackageDto packageDto = checkAndGet(packageId);
        packageDto.setName(name);
        packageDto.setDescription(description);
        packageDto.setProductIds(productIds);

        packageRepository.save(packageDto);

        return preparePackage(packageDto, currency);
    }

    @Override
    public void deletePackage(final String packageId) {
        final PackageDto packageDto = checkAndGet(packageId);
        packageRepository.delete(packageDto);
    }

    /**
     * This method prepares a Package in a currency requested by a client.
     *
     * @param packageDto
     *            The package stored in the database.
     * @param currency
     *            The currency requested by the client (USD by default).
     * @return Package containing products information and currency requested by client.
     */
    private Package preparePackage(final PackageDto packageDto, final String currency) {
        final List<Product> packageProductList = new ArrayList<>();
        for (final String productId : packageDto.getProductIds()) {
            packageProductList.add(productService.getProduct(productId, currency));
        }

        return new Package(packageDto.getId(), packageDto.getName(), packageDto.getDescription(), packageProductList);
    }
}