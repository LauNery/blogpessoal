package com.generation.blogpessoal.configuration;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
public class SwaggerConfig { 

	@Bean
	OpenAPI springBlogPessoalOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Projeto Blog Pessoal")
						.description("Projeto Blog Pessoal - Generation Brasil")
						.version("v.0.0.1")
				.license(new License()
						.name("Laura Nery")
						.url("lauran@genstudents.org"))
				.contact(new Contact()
						.name("Laura Nery")
						.url("https://github.com/LauNery")
						.email("laura.olivernery@gmail.com")))
				.externalDocs(new ExternalDocumentation()
						.description("GitHub")
						.url("https://github.com/LauNery"));
	}
	@Bean
	OpenApiCustomizer customerGlobalHeaderOpenApiCustomizer() {
		
		return OpenAPI -> {
			OpenAPI.getPaths().values().forEach(PathItem -> PathItem.readOperations()
					.forEach(Operation -> {
						
						ApiResponses apiResponses = Operation.getResponses();
						
						apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
						apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido!"));
						apiResponses.addApiResponse("204", createApiResponse("Objeto Excluído!"));
						apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição!"));
						apiResponses.addApiResponse("401", createApiResponse("Acesso não Autorizado!"));
						apiResponses.addApiResponse("403", createApiResponse("Acesso Proibido!"));
						apiResponses.addApiResponse("404", createApiResponse("Objeto não Encontado!"));
						apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação!"));
					}));
		};
	}
	private ApiResponse createApiResponse(String message) {
		
		return new ApiResponse().description(message);
	}
}

