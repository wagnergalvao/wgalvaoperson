package br.com.wgalvao.wgalvaoperson.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        // Via EXTENSION. http://localhost:8080/person.xml DEPRECATED on SpringBoot 2.6

        // Via QUERY PARAM. http://localhost:8080/person?mediaType=xml
        /*
         * configurer.favorParameter(true)
         * .parameterName("mediaType").ignoreAcceptHeader(true)
         * .useRegisteredExtensionsOnly(false)
         * .defaultContentType(MediaType.APPLICATION_JSON)
         * .mediaType("json", MediaType.APPLICATION_JSON)
         * .mediaType("xml", MediaType.APPLICATION_XML);
         */

        // Via HEADER PARAM. http://localhost:8080/person

        configurer.favorParameter(false)
                .ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML);

    }

}
