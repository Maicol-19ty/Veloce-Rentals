package cue.edu.co.velocerentals.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ApplicationLifecycleListener implements ServletContextListener {

    // Logs a message when the application is initialized.
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Application initialized");
    }

    // Logs a message when the application is destroyed.
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Application destroyed");
    }
}
