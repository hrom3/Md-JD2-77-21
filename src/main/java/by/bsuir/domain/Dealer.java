package by.bsuir.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class Dealer {

    private Long id;
    private String name;
    private Date openDate;
    private String locationDescription;
    private Long locationId;
    private Timestamp created;
    private Timestamp changed;
    private Integer openHour;
    private Integer closeHour;

    public Dealer() {
    }

    public Dealer(Long id, String name, Date openDate,
                  String locationDescription, Long locationId,
                  Timestamp created, Timestamp changed,
                  Integer openHour, Integer closeHour) {
        this.id = id;
        this.name = name;
        this.openDate = openDate;
        this.locationDescription = locationDescription;
        this.locationId = locationId;
        this.created = created;
        this.changed = changed;
        this.openHour = openHour;
        this.closeHour = closeHour;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getChanged() {
        return changed;
    }

    public void setChanged(Timestamp changed) {
        this.changed = changed;
    }

    public Integer getOpenHour() {
        return openHour;
    }

    public void setOpenHour(Integer openHour) {
        this.openHour = openHour;
    }

    public Integer getCloseHour() {
        return closeHour;
    }

    public void setCloseHour(Integer closeHour) {
        this.closeHour = closeHour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dealer dealer = (Dealer) o;
        return Objects.equals(id, dealer.id)
                && Objects.equals(name, dealer.name)
                && Objects.equals(openDate, dealer.openDate)
                && Objects.equals(locationDescription,
                dealer.locationDescription)
                && Objects.equals(locationId, dealer.locationId)
                && Objects.equals(created, dealer.created)
                && Objects.equals(changed, dealer.changed)
                && Objects.equals(openHour, dealer.openHour)
                && Objects.equals(closeHour, dealer.closeHour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, openDate, locationDescription,
                locationId, created, changed, openHour, closeHour);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.JSON_STYLE);
    }
}
