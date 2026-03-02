package fi.metropolia.jarkkaka.prj.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerAddressDto {
    private Integer id;

    @JsonProperty("street_address")
    private String streetAddress;
    @JsonProperty("postal_code")
    private String postalCode;
    private String city;
    private String country;

    private Integer customerId;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getStreetAddress() { return streetAddress; }
    public void setStreetAddress(String streetAddress) { this.streetAddress = streetAddress; }

    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }
}