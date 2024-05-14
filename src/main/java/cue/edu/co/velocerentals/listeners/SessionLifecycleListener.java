package cue.edu.co.velocerentals.listeners;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionLifecycleListener implements HttpSessionListener {

    // Logs a message when a new session is created, along with the session ID.
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("Session created: " + se.getSession().getId());
    }

    // Logs a message when a session is destroyed, along with the session ID.
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("Destroyed session: " + se.getSession().getId());
    }
}
