package com.bookstore.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI bookstoreOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Bookstore API - Kamlesh Edition")
                        .description("Spring Boot backend API for managing books, users, cart, and orders.\n\nControllers located at `/com.bookstore.controller`")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Kamlesh")
                                .email("kamlesh@example.com")
                                //.url("https://github.com/your-github") // optional
                        )
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")
                        )
                );
    }
}
