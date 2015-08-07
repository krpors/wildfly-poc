package nl.omgwtfbbq.wildflypoc.api;

/**
 * Holder class for login input data.
 */
public class ApiLoginInput {

    private String username;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
