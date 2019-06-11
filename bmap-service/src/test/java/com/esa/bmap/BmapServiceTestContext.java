package com.esa.bmap;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages = { "com.esa.bmap.external.*","com.esa.bmap.service.data","com.esa.bmap.service.implement", "com.esa.bmap.service.sample.implement", "com.esa.bmap.service.catalogue.implement", "com.esa.bmap.model", "com.esa.bmap.geoserver.loaders.interfaces", "com.esa.bmap.geoserver.loaders", "com.esa.bmap.dao.catalogue.data", "com.esa.bmap.external.service.dataprocessing","com.esa.bmap.service.acquisition.implement"  })
@EnableJpaRepositories("com.esa.bmap.*")
public class BmapServiceTestContext {

}
