package com.esgi.ykeoxay.shopping.Interface;

import com.esgi.ykeoxay.shopping.Model.Product;

import java.util.ArrayList;

/**
 * Created by ykeox on 29/11/2016.
 */

public interface ProductListParserResponse {
    void responseParsed(ArrayList<Product> products);
}
