package com.esa.bmap;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages = {"com.esa.bmap.dao.common", "com.esa.bmap.model"})
public class BmapDaoTestContext {

}
