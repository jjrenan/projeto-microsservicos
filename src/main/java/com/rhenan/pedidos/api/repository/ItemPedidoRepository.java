package com.rhenan.pedidos.api.repository;

import com.rhenan.pedidos.api.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, UUID> {
}
