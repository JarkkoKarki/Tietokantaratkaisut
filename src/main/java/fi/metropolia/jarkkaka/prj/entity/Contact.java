package fi.metropolia.jarkkaka.prj.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;
    @Column(name = "reference", columnDefinition = "char")
    private String reference;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", unique = true)
    private Customer customer;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
}