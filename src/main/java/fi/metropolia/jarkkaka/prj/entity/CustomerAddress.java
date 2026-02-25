package fi.metropolia.jarkkaka.prj.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name="customeraddresses")
public class CustomerAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;
    private String street_address;
    private String postal_code;
    private String city;
    private String country;

    public Integer getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPostalcode() {
        return postal_code;
    }

    public String getStreetAddress() {
        return street_address;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPostalcode(String postalcode) {
        this.postal_code = postalcode;
    }

    public void setStreetAddress(String streetAddress) {
        this.street_address = streetAddress;
    }
}