package cue.edu.co.velocerentals.listeners;

import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class SessionAttributeListener implements HttpSessionAttributeListener {

    // Logs a message when a new attribute is added to the session, along with the attribute name and value.
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println("Attribute added to session: " + event.getName() + " = " + event.getValue());
    }

    // Logs a message when an attribute is removed from the session, along with the attribute name and value.
    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.println("Attribute removed from session: " + event.getName() + " = " + event.getValue());
    }

    // Logs a message when an attribute is replaced in the session, along with the attribute name and value.
    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.println("Replaced attribute in session: " + event.getName() + " = " + event.getValue());
    }
}
