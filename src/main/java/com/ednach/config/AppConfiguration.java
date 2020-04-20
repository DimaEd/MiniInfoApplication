package com.ednach.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.ednach")
@Import({WebConfiguration.class, DatabaseConfiguration.class, SecurityConfiguration.class, SwaggerConfiguration.class})
public class AppConfiguration {

}
