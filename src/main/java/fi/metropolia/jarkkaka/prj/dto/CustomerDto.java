package fi.metropolia.jarkkaka.prj.dto;

import java.util.ArrayList;
import java.util.List;

public class CustomerDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private List<OrderDto> orders;
    private List<CustomerAddressDto> addresses;

    public CustomerDto() {
        this.orders = new ArrayList<>();
        this.addresses = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public String getFirstname() {
        return firstName;
    }

    public String getLastname() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstname(String firstName) {
        this.firstName = firstName;
    }

    public void setLastname(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
