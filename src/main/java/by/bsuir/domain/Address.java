package by.bsuir.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

public class Address {
    private Long id;
    private Long userId;
    private Long locationId;
    private String type;

    public Address() {
    }

    public Address(Long id, Long userId, Long locationId, String type) {
        this.id = id;
        this.userId = userId;
        this.locationId = locationId;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) && Objects.equals(userId, address.userId) && Objects.equals(locationId, address.locationId) && Objects.equals(type, address.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, locationId, type);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.JSON_STYLE);
    }
}
