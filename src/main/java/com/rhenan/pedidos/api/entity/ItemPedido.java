package com.rhenan.pedidos.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "item_pedido")
public class ItemPedido {

    @Id
    private UUID id = UUID.randomUUID();

    @ManyToOne
    private Produto produto;

    private Integer quantidade;

    @ManyToOne
    private Pedido pedido;

}
