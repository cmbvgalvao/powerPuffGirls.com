package org.example.service;

import org.example.model.Product;
import org.example.persistence.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductDao productDao;

    @Autowired
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Product get(Integer id) {
        return productDao.findById(id);
    }

    @Transactional
    public Product save(Product product) {
        return productDao.saveOrUpdate(product);
    }

    @Transactional
    public void delete(Integer id) {
        Product product = Optional.ofNullable(productDao.findById(id))
                .orElseThrow(NoSuchFieldError::new);

        productDao.delete(id);
    }

    public List<Product> list() {
        return productDao.findAll();
    }

}
