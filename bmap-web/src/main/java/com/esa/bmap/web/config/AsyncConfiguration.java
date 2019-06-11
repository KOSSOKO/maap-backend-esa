/*
 * $Id$
 *
 * ======================================================
 *
 * Project : Biomass
 * Produit par Capgemini.
 *
 * ======================================================
 * HISTORIQUE
 * FIN-HISTORIQUE
 * ======================================================
 */
package com.esa.bmap.web.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


/**
 * @author Capgemini
 * @version 0.0.1
 */
@Configuration
public class AsyncConfiguration {

    /**
     * Async executor.
     *
     * @return the executor
     */
    @Bean
    public Executor asyncExecutor() {
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("bmap-");
        executor.initialize();
        return executor;
    }

}
