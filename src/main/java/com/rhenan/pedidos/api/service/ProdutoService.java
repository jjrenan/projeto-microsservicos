package com.rhenan.pedidos.api.service;

import com.rhenan.pedidos.api.entity.ItemPedido;
import com.rhenan.pedidos.api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public void save(List<ItemPedido> itens) {

        for (ItemPedido item : itens) {
            produtoRepository.save(item.getProduto());
        }
    }
}
