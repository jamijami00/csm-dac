package com.victor.csmdac.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Victor Verdan",
                        email = "victorverdan@id.uff.br"
                ),
                description = "Documentação OpenApi para a api",
                title = "CSM - DAC",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "Server H2 local",
                        url = "http://localhost:8080"
                )
        }
)
public class OpenApiConfig {
}
