package fi.metropolia.jarkkaka.prj.dto;

import java.math.BigDecimal;
import java.util.List;

public class ProductDto {
    private Integer id;
    private String name;
    private BigDecimal price;
    private List<Integer> supplierIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Integer> getSupplierIds() {
        return supplierIds;
    }

    public void setSupplierIds(List<Integer> supplierIds) {
        this.supplierIds = supplierIds;
    }
}