package nl.omgwtfbbq.wildflypoc.resources;

import nl.omgwtfbbq.wildflypoc.api.ApiBaseResponse;
import nl.omgwtfbbq.wildflypoc.api.ApiCustomerInput;
import nl.omgwtfbbq.wildflypoc.entities.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
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

    /**
     * Create a new customer using ApiCustomerInpu as input. Use @Valid to enable bean validation?
     *
     * @param input The input.
     * @return A JAX-RS response.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.REQUIRED)
    public Response create(@Valid ApiCustomerInput input) {
        Customer c = new Customer(input.getName(), input.getSurname(), new Date());
        manager.merge(c);

        return Response.ok().build();
    }
}

