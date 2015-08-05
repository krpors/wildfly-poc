package nl.omgwtfbbq.wildflypoc.api;

import javax.json.JsonString;
import java.util.ArrayList;
import java.util.List;

/**
 * Base response class for JSON API calls.
 */
public class ApiBaseResponse {

    /**
     * Embeddable response data.
     */
    private Object data;

    /**
     * List with errors, can be null.
     */
    private List<ApiError> apiErrorList;

    /**
     * Meta data.
     */
    private Object meta;

    /**
     * Returns all apiErrorList as an array.
     * @return The apiErrorList.
     */
    public List<ApiError> getErrors() {
        return apiErrorList;
    }

    public void addError(ApiError error) {
        if (apiErrorList == null) {
            apiErrorList = new ArrayList<ApiError>();
        }

        apiErrorList.add(error);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
