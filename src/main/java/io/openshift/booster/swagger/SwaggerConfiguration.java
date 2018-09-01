package io.openshift.booster.swagger;

import com.google.common.base.Predicates;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Florent Benoit
 */
@Configuration
public class SwaggerConfiguration {

        @Bean
        public Docket docket() {
            return new Docket(DocumentationType.SWAGGER_2).select()
                    .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                    .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
                    .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.cloud")))
                    .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.data.rest.webmvc")))
                    .build();
        }



}
