package org.example.dto;

import javax.validation.constraints.*;

public class ProductDto {

    private Integer id;

    private String item_name;

    private Integer items_in_stock;

    private Double price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public Integer getItems_in_stock() {
        return items_in_stock;
    }

    public void setItems_in_stock(Integer items_in_stock) {
        this.items_in_stock = items_in_stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
