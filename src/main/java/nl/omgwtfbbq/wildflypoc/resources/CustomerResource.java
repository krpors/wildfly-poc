package nl.omgwtfbbq.wildflypoc.resources;

import nl.omgwtfbbq.wildflypoc.api.ApiBaseResponse;
import nl.omgwtfbbq.wildflypoc.api.ApiError;
import nl.omgwtfbbq.wildflypoc.entities.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * ApplicationScoped: injections of EntityManager will happen only once.
 */
@ApplicationScoped
@Path("customers")
public class CustomerResource {

    /**
     * Refers to the persistence.xml config file.
     */
    @PersistenceContext(name = "my-unit")
    private EntityManager manager;


    /**
     * Fetches all customers.
     */
    @GET
    @Path("")
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        // Note that the Customer is case sensitive
        TypedQuery<Customer> tq = manager.createQuery("select c from Customer c", Customer.class);
        List<Customer> results = tq.getResultList();


        ApiBaseResponse abr = new ApiBaseResponse();
        abr.setData(results);

        return Response.ok(abr).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomer(@PathParam("id") Long id) {
        TypedQuery<Customer> tc = manager.createQuery("select c from Customer c where c.id = :id", Customer.class);
        tc.setParameter("id", id);

        List<Customer> resultlist = tc.getResultList();
        if (resultlist.size() == 1) {
            ApiBaseResponse abr = new ApiBaseResponse();
            abr.setData(resultlist);
            return Response.ok(abr).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("err")
    @Produces(MediaType.APPLICATION_JSON)
    public Response what() {
        ApiBaseResponse abr = new ApiBaseResponse();

        ApiError err = new ApiError();
        err.setCode("1");
        err.setDetail("Something happened");
        err.setStatus(500);
        err.setTitle("Whoops!");

        abr.addError(err);

        return Response.ok(abr).build();
    }
}

