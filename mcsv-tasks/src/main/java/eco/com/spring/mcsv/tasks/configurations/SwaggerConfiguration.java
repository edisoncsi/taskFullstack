package eco.com.spring.mcsv.tasks.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
 * @author edisoncsi on 11/10/23
 * @project mcsv-task
 */
@Configuration
public class SwaggerConfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("eco.com.spring.mcsv.tasks.controllers"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("My REST API",
                "Task management API.",
                "1.0",
                "Terms of service",
                new Contact("Edison", "www.edisoncsi.com", "edisoncsi@eco.com"),
                "License of API",
                "API license URL",
                Collections.emptyList());
    }
}
