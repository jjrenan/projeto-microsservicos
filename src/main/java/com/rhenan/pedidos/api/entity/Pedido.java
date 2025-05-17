package com.rhenan.pedidos.api.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rhenan.pedidos.api.entity.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    private UUID id = UUID.randomUUID();

    private String cliente;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens = new ArrayList<>();

    @Column(name = "valor_total")
    private Double valorTotal;

    @Column(name = "email_notificacao")
    private String emailNotificacao;

    @Enumerated(EnumType.STRING)
    private Status status = Status.EM_PROCESSECAMENTO;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataHora = LocalDate.now();
}
