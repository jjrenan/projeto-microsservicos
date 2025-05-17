package com.rhenan.pedidos.api.service;

import com.rhenan.pedidos.api.entity.ItemPedido;
import com.rhenan.pedidos.api.entity.Pedido;
import com.rhenan.pedidos.api.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;


    public List<ItemPedido> save(List<ItemPedido> itens) {
        return itemPedidoRepository.saveAll(itens);
    }

    public void save(ItemPedido itemPedido) {
        itemPedidoRepository.save(itemPedido);
    }

    public void updatedItemPedido(List<ItemPedido> itemPedidos, Pedido pedido) {
        for (ItemPedido itemPedido : itemPedidos) {
            itemPedido.setPedido(pedido);// informando ao item o seu pedido
            this.save(itemPedido);
        }
    }
}
