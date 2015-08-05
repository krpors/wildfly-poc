package nl.omgwtfbbq.wildflypoc.api.providers;

import nl.omgwtfbbq.wildflypoc.api.ApiBaseResponse;
import nl.omgwtfbbq.wildflypoc.api.ApiError;
import org.jboss.resteasy.api.validation.ResteasyConstraintViolation;
import org.jboss.resteasy.api.validation.ResteasyViolationException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Bean validation exception mapper. I tried using ConstraintViolationException but the toResponse was not invoked?
 * I think Resteasy is interfering or something. I'm now doing it with the ResteasyViolationException. It seems
 * to work, so far, though it's tied to Resteasy obviously, and will not work with Jersey.
 */
@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ResteasyViolationException> {

    @Override
    public Response toResponse(ResteasyViolationException exception) {
        ApiBaseResponse response = new ApiBaseResponse();

        // Add all violations as ApiErrors to the response.
        for (ResteasyConstraintViolation violation : exception.getViolations()) {
            String message = String.format("%s at %s (value is: '%s')",
                    violation.getMessage(), violation.getPath(), violation.getValue());
            ApiError error = ApiError.create()
                    .code("1000")
                    .title("Constraint violation")
                    .detail(message)
                    .build();
            response.addError(error);
        }

        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(response)
                .build();
    }
}
