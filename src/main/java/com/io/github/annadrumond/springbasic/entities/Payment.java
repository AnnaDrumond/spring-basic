package com.io.github.annadrumond.springbasic.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;


@Entity
@NoArgsConstructor
@RequiredArgsConstructor // Neste caso cada um dos atributos que QUERO no construtor devem ter a annotation  @NonNull
@Slf4j //fazer log da execução dos nossos serviços
@Table(name = "PAYMENT_TABLE")
public class Payment implements Serializable {

    @Id @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter @NonNull private Instant moment;

    @OneToOne @JsonIgnore
    @MapsId // Fica neste "lado" pois Payment é dependente de Orderk, ou seja, Order é o dono da relação
    //A anotação @MapsId é usada para especificar que o identificador de entidade é mapeado pelo @OneToOne associado (ou pelo @ManyToOne atualmente anotado)
    // https://strn.com.br/artigos/2018/12/11/todas-as-anota%C3%A7%C3%B5es-do-jpa-anota%C3%A7%C3%B5es-de-mapeamento/
    //Posso ter um Order sem Payment, mas para existir um Payment preciso obrigatoriamente ter um Order
    @Getter @Setter @NonNull private Order order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
