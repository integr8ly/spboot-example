package io.openshift.booster.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Florent Benoit
 */
@Configuration
public class SwaggerConfiguration {

        @Bean
        public Docket docket() {
            return new Docket(DocumentationType.SWAGGER_2);
        }

}
