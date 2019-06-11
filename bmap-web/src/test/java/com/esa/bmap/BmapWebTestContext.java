package com.esa.bmap;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages = {"com.esa.bmap.service", "com.esa.bmap.model", "com.esa.bmap.controller,com.esa.bmap.external"})
public class BmapWebTestContext {

}
