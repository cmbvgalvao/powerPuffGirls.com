package org.example.dto.assembler;

import org.example.dto.ProductDto;
import org.example.model.Product;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoAssembler implements GenericDtoAssembler<Product, ProductDto> {


    private ProductService productService;

    @Autowired
    public void setUserService(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public ProductDto convertToDto(Product product) {
            ProductDto productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setItem_name(product.getItem_name());
            productDto.setPrice(product.getPrice());
            productDto.setItems_in_stock(product.getItems_in_stock());
            return productDto;
    }

    @Override
    public Product convertFromDto(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setItem_name(productDto.getItem_name());
        product.setPrice(productDto.getPrice());
        product.setItems_in_stock(productDto.getItems_in_stock());
        return product;
    }
}
