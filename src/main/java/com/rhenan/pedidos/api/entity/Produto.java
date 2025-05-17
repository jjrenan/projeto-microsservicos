package com.rhenan.pedidos.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    private UUID id = UUID.randomUUID();

    private Double valor;
    private String nome;
}
