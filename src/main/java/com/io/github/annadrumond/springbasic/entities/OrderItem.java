package com.io.github.annadrumond.springbasic.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.io.github.annadrumond.springbasic.entities.pk.OrderItemPK;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor // Construtor vazio
@Slf4j //fazer log da execução dos nossos serviços
@Table(name = "ORDER_ITEM_TABLE")
public class OrderItem implements Serializable {

    @EmbeddedId
    // Como ela não será auto-gerada pela BD, é necessário instanciar para não resultar em NullPointerException
    private OrderItemPK compositeId = new OrderItemPK();
    @Getter @Setter
    private Integer quantity;
    @Getter @Setter
    private double price;

    public OrderItem(Order order, Product product, Integer quantity, double price) {
        compositeId.setOrder(order);// chamada para os setter que estão em OrderItemPK
        compositeId.setProduct(product);
        this.quantity = quantity;
        this.price = price;
    }

    //Os quatro métodos abaixo foram feitos manualmente
    //Criar os getters que darão acesso ao produto e ao pedido
    @JsonIgnore //corta/interrompe a associação de via dupla, para não gerar erros lazily
    public Order getOrder(){
        return compositeId.getOrder();
    }

    public Product getProduct(){
        return compositeId.getProduct();
    }

    //Criar os setters que darão acesso a modificar o produto e o pedido
    public void setOrder(Order order){
        compositeId.setOrder(order);
    }

    public void setProduct(Product product){
        compositeId.setProduct(product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(compositeId, orderItem.compositeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(compositeId);
    }
}
