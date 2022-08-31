package com.io.github.annadrumond.springbasic.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor // Neste caso cada um dos atributos que QUERO no construtor devem ter a annotation  @NonNull
@Slf4j //fazer log da execução dos nossos serviços
@Table(name = "PRODUCT_TABLE")
public class Product implements Serializable {

    @Id  @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter @Setter @NonNull private String name;
    @Getter @Setter @NonNull private String description;
    @Getter @Setter @NonNull private double price;
    @Getter @Setter @NonNull private String imgUrl;


    //Um produto pode ter várias categorias
    @Getter
    @ManyToMany
    //joinColumns = @JoinColumn(name="product_id") define a FK da
    //inverseJoinColumns define a FK da outra entidade (Category)
    @JoinTable(name = "PRODUCT_CATEGORY",
            joinColumns = @JoinColumn(name="product_id"),
            inverseJoinColumns = @JoinColumn(name="category_id"))
    //O Set vai garantir que não terei mais do que um produto de uma mesma categoria
    //O mesmo produto não pode ter a mesma categoria, mais de uma vez
    private Set<Category> categories = new HashSet<>();

    //Produto está associado a OrderItem e este por sua vez está associado a Order
    //Preciso aceder aqui em Product, uma lista de orders
    // OrderItem tem o compositeId e este é que está associado ao product
    @OneToMany(mappedBy = "compositeId.product")
    private Set<OrderItem> orderItems = new HashSet<>();

    //Método criado manualmente
    //Como aqui eu somente tenho acesso a coleção de orderItems, basta percorrer a mesma extraindo as orders
    @JsonIgnore //corta/interrompe a associação de via dupla, para não gerar erros lazily
    public Set<Order> getOrderItems() {
        Set<Order> orders = new HashSet<>();
        for (OrderItem orderItem: orderItems) {
            orders.add(orderItem.getOrder());
        }
        return orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
