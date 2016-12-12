package com.esgi.ykeoxay.shopping.Model;

public class Product {

    private String name;
    private Integer id;
    private Double price;
    private Integer quantity;

    public Product(){

    }

    public String getName()
    {
        return this.name;
    }

    public Integer getId()
    {
        return this.id;
    }

    public Double getPrice()
    {
        return this.price;
    }

    public Integer getQuantity(){ return this.quantity; }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public void setPrice(Double price) {this.price = price; }

    public void setQuantity(Integer quantity) { this.quantity = quantity; }
}
