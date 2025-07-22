package com.commerce.ec.global;

import java.util.ArrayList;
import java.util.List;

import com.commerce.ec.model.product.Product;

public class GlobalData {

    public static List<Product> products ;
    
    static {
        products = new ArrayList<>();
    }
}
