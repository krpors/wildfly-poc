package nl.omgwtfbbq.wildflypoc.api;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Input bean for creating a customer.
 */
public class ApiCustomerInput {

    @NotNull
    @Size(min = 2, max = 20)
    private String name;

    @NotNull
    @Size(min = 2)
    private String surname;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
