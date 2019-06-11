package com.esa.bmap.external;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages = {"com.esa.bmap.external.service.dataprocessing.implement" })
public class BmapServiceExternalTestContext {

}
