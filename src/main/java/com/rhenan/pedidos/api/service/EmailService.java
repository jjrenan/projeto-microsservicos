package com.rhenan.pedidos.api.service;


import com.rhenan.pedidos.api.entity.Pedido;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarEmail(Pedido pedido) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("pedidos-api@company.com");
        message.setTo(pedido.getEmailNotificacao());
        message.setSubject("Pedido de compra");
        message.setText(this.gerarMensagem(pedido));
        mailSender.send(message);
    }

    private String gerarMensagem(Pedido pedido) {
        String pedidoId = pedido.getId().toString();
        String cliente = pedido.getCliente();
        String valor = String.valueOf(pedido.getValorTotal());
        String status = pedido.getStatus().name();

        String format = String.format("Oá %s seu pedido de n° %s no valor de %s, foi realizado com sucesso." +
                "\nStatus: %s.", cliente, pedidoId, valor, status);
        return format;
    }
}
