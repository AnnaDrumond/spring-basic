package com.io.github.annadrumond.springbasic.entities.pk;

import com.io.github.annadrumond.springbasic.entities.Order;
import com.io.github.annadrumond.springbasic.entities.Product;

//Aqui será definida a chave composta da relação
public class OrderItemPK {

    private Order order;
    private Product product;
}
