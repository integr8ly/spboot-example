package io.openshift.booster.swagger;

import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author Florent Benoit
 */
@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .protocols(new HashSet<>(Lists.newArrayList("http", "https", "ws", "wss")))
                .globalResponseMessage(RequestMethod.PUT,
                        Lists.newArrayList(
                                new ResponseMessageBuilder().code(201).message("Created")
                                        .responseModel(new ModelRef("Fruit")).build()
                        )
                )
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.DELETE,
                        Lists.newArrayList(
                                new ResponseMessageBuilder().code(204).message("No Content")
                                        .responseModel(new ModelRef("")).build()
                        )
                )
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
                .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.cloud")))
                .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.data.rest.webmvc")))
                .build();
    }

}
