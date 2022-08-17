package com.example.userbackend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("List API User Management")
                        .description("Danh sách API ứng dụng quản lý user")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Bùi Hiên")
                                .email("hien@techmaster.vn"))
                );
    }
}
