package com.io.github.annadrumond.springbasic.entities.pk;

import com.io.github.annadrumond.springbasic.entities.Order;
import com.io.github.annadrumond.springbasic.entities.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

//Nesta classe auxiliar será definida a chave composta da relação
//No modelo de dominio a OrderItem é um entidade fraca (não possui existência própria,
// ex: a existência da entidade livro, seria dependente da existência da entidade livraria)
@Embeddable
public class OrderItemPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "order_id")//nome da FK
    @Getter @Setter private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")//nome da FK
    @Getter @Setter private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemPK that = (OrderItemPK) o;
        return Objects.equals(order, that.order) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product);
    }
}
