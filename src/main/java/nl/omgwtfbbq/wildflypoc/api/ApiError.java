package nl.omgwtfbbq.wildflypoc.api;

/**
 * Error class for JSON API.
 */
public class ApiError {
    /**
     * Uniquely identifying the error ID.
     */
    private int id;

    /**
     * HTTP status code.
     */
    private int status;

    /**
     * Application specific error code.
     */
    private String code;

    /**
     * Human readable summary of the error.
     */
    private String title;

    /**
     * Explicit detail of the error.
     */
    private String detail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public static ApiErrorBuilder create() {
        return new ApiErrorBuilder();
    }

    /**
     * Builder class for the ApiError.
     */
    public static class ApiErrorBuilder {
        private ApiError err;

        public ApiErrorBuilder() {
            this.err = new ApiError();
        }

        public ApiErrorBuilder title(String title) {
            this.err.setTitle(title);
            return this;
        }

        public ApiErrorBuilder code(String code) {
            this.err.setCode(code);
            return this;
        }

        public ApiErrorBuilder detail(String detail) {
            this.err.setDetail(detail);
            return this;
        }

        public ApiError build() {
            return this.err;
        }
    }
}
