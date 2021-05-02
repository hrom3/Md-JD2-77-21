package by.bsuir.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Date;
import java.util.Objects;

public class Cars {
    private Long id;
    private Long dealerId;
    private Integer price;
    private String name;
    private Date productionDate;
    private String model;
    private Long owner;

    public Cars() {
    }

    public Cars(Long id, Long dealerId, Integer price, String name,
                Date productionDate, String model, Long owner) {
        this.id = id;
        this.dealerId = dealerId;
        this.price = price;
        this.name = name;
        this.productionDate = productionDate;
        this.model = model;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDealerId() {
        return dealerId;
    }

    public void setDealerId(Long dealerId) {
        this.dealerId = dealerId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getOwner() {
        return owner;
    }

    public void setOwner(Long owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cars cars = (Cars) o;
        return Objects.equals(id, cars.id)
                && Objects.equals(dealerId, cars.dealerId)
                && Objects.equals(price, cars.price)
                && Objects.equals(name, cars.name)
                && Objects.equals(productionDate, cars.productionDate)
                && Objects.equals(model, cars.model)
                && Objects.equals(owner, cars.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dealerId, price, name,
                productionDate, model, owner);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.JSON_STYLE);
    }
}
