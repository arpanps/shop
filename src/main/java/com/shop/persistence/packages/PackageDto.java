package com.shop.persistence.packages;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Arpan Khandelwal
 *
 */
@Entity
@Table(name = "packages")
public class PackageDto {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ElementCollection
    @CollectionTable(name = "package_products")
    private List<String> productIds;

    public PackageDto() {
        // Default constructor for Hibernate.
    }

    public PackageDto(final String id, final String name, final String description, final List<String> productIds) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.productIds = productIds;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public List<String> getProductIds() {
        return productIds;
    }

    public void setProductIds(final List<String> productIds) {
        this.productIds = productIds;
    }
}