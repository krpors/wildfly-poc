package nl.omgwtfbbq.wildflypoc.misc;

import nl.omgwtfbbq.wildflypoc.entities.Customer;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;

/**
 * Initialization, only once.
 */
@Singleton
@Startup
public class Init {

    @PersistenceContext(name = "my-unit")
    private EntityManager em;

    @PostConstruct
    @Transactional(Transactional.TxType.REQUIRED)
    public void startup() {
        em.merge(new Customer("Jack", "Jackson", new Date()));
        em.merge(new Customer("Bill", "Billson", new Date()));
        em.merge(new Customer("Will", "Wackyson", new Date()));
    }
}
