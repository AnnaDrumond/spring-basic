package com.io.github.annadrumond.springbasic.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "USER_TABLE")
@NoArgsConstructor
@RequiredArgsConstructor // Neste caso cada um dos atributos que QUERO no construtor devem ter a annotation  @NonNull
@Slf4j //fazer log da execução dos nossos serviços
@ToString (of = {"name" , "id"})// Quero toString somente destes
//Caso eu quisesse excluir alguns seria exclude={"name", "phone"}, por exemplo
//@EqualsAndHashCode(of={"id"}) - dentro coloco o quero como parametro de comparação
public class User implements Serializable {

    //https://medium.com/collabcode/projeto-lombok-escrevendo-menos-c%C3%B3digo-em-java-8fc87b379209
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter private Long id;
    @Getter @Setter @NonNull private String name;
    @Getter @Setter @NonNull private String email;
    @Getter @Setter @NonNull private String phone;
    @Getter @Setter @NonNull private String password;

    //Um cliente tem vários pedidos
    @Getter
    @OneToMany(mappedBy = "client")
    @JsonIgnore
   // @ToString.Exclude // não quero esta informação no meu toString
    //Instanciar para garantir que a coleção comece vazia, porém NÂO NULA
    private Set<Order> orders = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
