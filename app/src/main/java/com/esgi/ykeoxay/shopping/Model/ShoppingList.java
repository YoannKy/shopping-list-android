package com.esgi.ykeoxay.shopping.Model;

/**
 * Created by ykeox on 28/11/2016.
 */

public class ShoppingList {

    private String name;
    private Integer id;
    private Boolean completed;

    public ShoppingList(){

    }

    public String getName()
    {
        return this.name;
    }

    public Integer getId()
    {
        return this.id;
    }

    public Boolean getCompleted()
    {
        return this. completed;
    }


    public void setName(String name)
    {
        this.name = name;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public void setCompleted(Boolean completed) {this.completed = completed; }

}
