package com.framework.backend.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * Listener pour nettoyer proprement les ressources de la base de données
 * lors de l'arrêt de l'application, évitant ainsi les fuites mémoire.
 */
@WebListener
public class DatabaseCleanupListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseCleanupListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Application démarrée - DatabaseCleanupListener initialisé");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Arrêt de l'application - Nettoyage des drivers JDBC");
        
        // Désenregistrer tous les drivers JDBC
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                logger.info("Driver JDBC désenregistré: {}", driver.getClass().getName());
            } catch (SQLException e) {
                logger.error("Erreur lors du désenregistrement du driver JDBC: {}", 
                           driver.getClass().getName(), e);
            }
        }
        
        // Forcer le nettoyage de la mémoire
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        logger.info("Nettoyage terminé");
    }
}
