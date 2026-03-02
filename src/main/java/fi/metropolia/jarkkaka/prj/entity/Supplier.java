package fi.metropolia.jarkkaka.prj.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String contact_name;
    private String phone;
    private String email;
    @ManyToMany(mappedBy = "suppliers")
    @JsonIgnore
    private List<Product> products;
    @OneToMany(mappedBy = "supplier")
    List<SupplierAddress> address;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<SupplierAddress> getAddress() {
        return address;
    }

    public String getContact_name() {
        return contact_name;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public void setAddress(List<SupplierAddress> address) {
        this.address = address;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
