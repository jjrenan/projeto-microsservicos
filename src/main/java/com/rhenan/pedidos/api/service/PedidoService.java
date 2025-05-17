package com.rhenan.pedidos.api.service;

import com.rhenan.pedidos.api.entity.ItemPedido;
import com.rhenan.pedidos.api.entity.Pedido;
import com.rhenan.pedidos.api.repository.PedidoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PedidoService {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoService itemPedidoService;

    public Pedido enfileirarPedido(Pedido pedido) {
        rabbitTemplate.convertAndSend(exchangeName, "", pedido);
        log.info("Pedido enfileirado: {}", pedido.toString());
        return pedido;
    }

    public void save(Pedido pedido) {

        //salvamos os produtos
        produtoService.save(pedido.getItens());

        //salvamos os itens
        List<ItemPedido> itemPedidos = itemPedidoService.save(pedido.getItens());

        //salvamos o pedido
        pedidoRepository.save(pedido);

        //atualiza o item pedido definindo o pedido ao qual ele faz parte
        itemPedidoService.updatedItemPedido(itemPedidos, pedido);

        log.info("Pedido salvo: {}", pedido.toString());
    }


}
