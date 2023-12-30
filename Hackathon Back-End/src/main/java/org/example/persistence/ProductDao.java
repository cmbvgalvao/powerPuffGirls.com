package org.example.persistence;

import org.example.model.Product;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao extends GenericDao<Product> {
    public ProductDao() {
        super(Product.class);
    }
}
