package fi.metropolia.jarkkaka.prj.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "supplieraddresses")
public class SupplierAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String street_address;
    private String postal_code;
    private String city;
    private String country;
    @ManyToOne
    private Supplier supplier;

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public String getStreet_address() {
        return street_address;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
