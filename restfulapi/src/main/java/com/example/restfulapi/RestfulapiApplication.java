package com.example.restfulapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "API Product",
				version = "1.0.0",
				description = "This project is only learning !",
				contact = @Contact(
						name = "Đỗ Trung Đạt",
						email = "dotrungdat.mda@gmail.com"
				)
		),
		servers = {
				@Server(
						description = "Local ENV",
						url = "http://localhost:8088"
				),
		},
		security = {
				@SecurityRequirement(
						name = "bearerAuth"
				)
		}
)
public class RestfulapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulapiApplication.class, args);
	}

}
