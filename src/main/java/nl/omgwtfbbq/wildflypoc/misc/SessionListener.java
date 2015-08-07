package nl.omgwtfbbq.wildflypoc.misc;

import javax.enterprise.context.ApplicationScoped;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@ApplicationScoped
@WebListener
public class SessionListener implements HttpSessionListener {

    private int sessionCount = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("Session created. Users with sessions: " + ++sessionCount);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("Session destroyed. Users with sessions: " + --sessionCount);

    }
}
