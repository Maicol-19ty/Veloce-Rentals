package cue.edu.co.velocerentals.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ApplicationLifecycleListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Application initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Application destroyed");
    }
}
