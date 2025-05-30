package com.owen.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("Blog Service API")
		                                    .description("API documentation for Blog Service.")
		                                    .version("1.0.0")
		                                    .contact(new Contact().name("Owen")
		                                                          .email("youngwhenball@gmail.com")))
		                    .externalDocs(new ExternalDocumentation().description("Project Repository")
		                                                             .url("https://github.com/OwenYyds/blog-service#"))
		                    // 添加JWT安全方案
		                    .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
		                    .components(new io.swagger.v3.oas.models.Components().addSecuritySchemes("bearerAuth", new SecurityScheme().name("Authorization")
		                                                                                                                               .type(SecurityScheme.Type.HTTP)
		                                                                                                                               .scheme("bearer")
		                                                                                                                               .bearerFormat("JWT")));
	}
}

