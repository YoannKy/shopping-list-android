package com.esgi.ykeoxay.shopping.Interface;

import com.esgi.ykeoxay.shopping.Model.Product;

import java.util.ArrayList;

public interface ProductListParserResponse {
    void responseParsed(ArrayList<Product> products);
}
