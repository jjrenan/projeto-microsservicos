package com.rhenan.pedidos.api.listener;


import com.rhenan.pedidos.api.entity.Pedido;
import com.rhenan.pedidos.api.entity.enums.Status;
import com.rhenan.pedidos.api.service.EmailService;
import com.rhenan.pedidos.api.service.PedidoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PedidoListener {

    @Autowired
    private EmailService emailService;

    @Autowired
    private PedidoService pedidoService;

    @RabbitListener(queues = "pedidos.v1.pedido-criado.gerar-processamento")
    public void enviarNotificacao(Pedido pedido) {

        emailService.enviarEmail(pedido);
        log.info("Notificacao gerada com sucesso: {}", pedido.toString());
    }

    @RabbitListener(queues = "pedidos.v1.pedido-criado.gerar-processamento")
    public void salvarPedido(Pedido pedido) {
        pedido.setStatus(Status.PROCESSADO);
        pedidoService.save(pedido);

    }
}
