package com.nekkoripay.payment;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Payment API", version = "1.0.0", description = "API Documentation"))
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
			.components(new Components())
			.info(new io.swagger.v3.oas.models.info.Info()
				.title("Payment API")
				.description("API Documentation for Payment Service")
				.version("1.0.0")
				.contact(new Contact().name("NekkoriPay").email("support@nekkoripay.com"))
				.license(new License().name("Apache 2.0").url("http://springdoc.org"))
			);
	}

    /*
    // 필요하다면 Security Scheme(인증/인가) 등을 설정할 수 있습니다.
    @SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
    )
    */
}
