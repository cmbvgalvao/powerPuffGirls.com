package org.example.controller;

import org.example.dto.ProductDto;
import org.example.dto.assembler.ProductDtoAssembler;
import org.example.model.Product;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/product")
public class MyAppController {

    private ProductService productService;

    private ProductDtoAssembler productDtoAssembler;

    @Autowired
    public void setProductDtoAssembler(ProductDtoAssembler productDtoAssembler) {
        this.productDtoAssembler = productDtoAssembler;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ProductDto> getProduct(@PathVariable Integer id){
        Product product = productService.get(id);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");

        return new ResponseEntity<>(productDtoAssembler.convertToDto(product), headers, HttpStatus.OK);
    }

    @GetMapping(value = "/",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<ProductDto>> getProduct(){

        List<Product> products = productService.list();

        return new ResponseEntity<>(productDtoAssembler.convertToDto(products), HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(value = "/",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ProductDto> addProduct(@Valid @RequestBody ProductDto productDto, BindingResult bindingResult){

        if (bindingResult.hasErrors() || productDto.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Product product = productDtoAssembler.convertFromDto(productDto);
        Product savedUser = productService.save(product);

        return new ResponseEntity<>(productDtoAssembler.convertToDto(savedUser),HttpStatus.CREATED);
    }
    @Transactional
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ProductDto> editProduct(@PathVariable Integer id, @Valid @RequestBody ProductDto productDto, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (productDto.getId() != null && !productDto.getId().equals(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (productService.get(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Product product = productDtoAssembler.convertFromDto(productDto);

        product.setId(id);
        Product savedProduct =  productService.save(product);

        return new ResponseEntity<>(productDtoAssembler.convertToDto(savedProduct), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer id){
        productService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
