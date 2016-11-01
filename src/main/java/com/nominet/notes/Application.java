package com.nominet.notes;

import java.util.TimeZone;

import org.h2.server.web.WebServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * Created by sandeep.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.nominet.notes"})
@EnableJpaRepositories(basePackages = {"com.nominet.notes.control.repository"})
public class Application extends SpringBootServletInitializer{
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);

    /**
     * The entry point, starts the application.
     *
     * @param args
     */
    public static void main(String[] args) {
    	log.debug("Notes app starting");
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        new SpringApplicationBuilder(Application.class).web(true).run(args);
        log.debug("Notes app started");
    }
    
    /**
     * H2 servelt registration bean
     * @return ServletRegistrationBean
     */
    @Bean
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/console/*");
        return registration;
    }
}
