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
@NoArgsConstructor // Construtor vazio
@RequiredArgsConstructor // Neste caso cada um dos atributos que QUERO no construtor devem ter a annotation  @NonNull
@Slf4j //fazer log da execução dos nossos serviços
@Table(name = "CATEGORY_TABLE")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Getter @Setter
    @NonNull
    private String name;

    //Um produto pode ter várias categorias
    @Getter
    @JsonIgnore
    @ManyToMany (mappedBy = "categories")
    //O Set vai garantir que não terei mais do que um produto de uma mesma categoria
    //O mesmo produto não pode ter a mesma categoria, mais de uma vez
    private Set<Product> products = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id.equals(category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
