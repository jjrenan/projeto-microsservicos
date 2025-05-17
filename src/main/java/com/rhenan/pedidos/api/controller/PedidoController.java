package com.rhenan.pedidos.api.controller;

import com.rhenan.pedidos.api.entity.Pedido;
import com.rhenan.pedidos.api.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Pedidos", description = "Recurso para criar Pedidos")
@Slf4j
@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoController {


    @Autowired
    private PedidoService pedidoService;

    @Operation(summary = "Cria um novo pedido", description = "criar novo pedido",
            responses = @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Pedido.class))))
    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
        log.info("Criando pedido: {}", pedido.toString());
        pedido = pedidoService.enfileirarPedido(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);

    }
}