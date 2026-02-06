package com.framework.backend;

import com.framework.backend.config.DataConfig;
import com.framework.backend.config.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Configuration d'initialisation de l'application web Spring MVC
 * Remplace le traditionnel web.xml
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        // Configuration de la couche service et data
        return new Class[] { DataConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // Configuration Spring MVC
        return new Class[] { WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        // Mappage du DispatcherServlet
        return new String[] { "/" };
    }
}