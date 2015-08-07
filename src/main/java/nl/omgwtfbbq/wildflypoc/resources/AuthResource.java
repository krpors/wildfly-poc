package nl.omgwtfbbq.wildflypoc.resources;

import nl.omgwtfbbq.wildflypoc.api.ApiBaseResponse;
import nl.omgwtfbbq.wildflypoc.api.ApiLoginInput;

import javax.enterprise.context.ApplicationScoped;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Resource belonging to authorization stuff.
 */
@ApplicationScoped
@Path("auth")
public class AuthResource {

    @Context
    private HttpServletRequest request;

    /**
     * Attempts to login using form params.
     *
     * @param input The input as an ApiLoginInput JSON formatted string thing.
     * @return Response with 200 OK, or 403 FORBIDDEN when a faulty combination was entered.
     */
    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticate(ApiLoginInput input) {
        ApiBaseResponse abr = new ApiBaseResponse();
        abr.setData("ok");

        System.out.printf("Requesting login for username '%s', password '%s'\n", input.getUsername(), input.getPassword());

        if ("kpors".equals(input.getUsername()) && "wildfly".equals(input.getPassword())) {
            request.getSession(true); // create a new session here.
            return Response.ok(abr).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    /**
     * Logs out a certain session.
     *
     * @return The session to invalidate.
     */
    @POST
    @Path("logout")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout() {
        if (request.getSession(false) == null) {
            System.out.println("Got no session, nothing to logout from.");
            ApiBaseResponse abr = new ApiBaseResponse();
            abr.setData("nothing to logout from");
            return Response.status(Response.Status.NOT_MODIFIED).entity(abr).build();
        }

        request.getSession(false).invalidate();

        ApiBaseResponse abr = new ApiBaseResponse();
        abr.setData("ok");

        return Response.ok(abr).build();
    }

    /**
     * Just some debugging stuff. Checks whether you're able to use this API call when a correct Cookie is provided.
     *
     * @return OK or forbidden.
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response stuff() {
        if (request.getSession(false) != null) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
