package com.io.github.annadrumond.springbasic.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "ORDER_TABLE")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter private Long id;
    //Instant é similar ao Date
    //Para formatar a data e garantir que esta seja exibida no formato ISO8601
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    @Getter @Setter @NonNull private Instant moment;

    //Um pedido tem um cliente
    //Cliente é o dono da relação
    @ManyToOne
    @JoinColumn(name = "client_id")
    @Getter @Setter @NonNull User client;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
