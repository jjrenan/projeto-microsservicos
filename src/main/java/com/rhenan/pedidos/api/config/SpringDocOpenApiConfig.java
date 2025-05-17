package com.rhenan.pedidos.api.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {

    public OpenAPI getOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Rest API - Pedidos")
                        .description("Api REST API realizacao de pedidos")
                        .license(new License().name("Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0.html"))
                        .contact(new Contact().name("Rhenan")
                        .email("rhenan@teste.com"))
                );
    }
}
