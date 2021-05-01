package by.bsuir.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;
import java.sql.Date;
import java.util.Objects;

public class User {

    private Long id;
    private String name;
    private String surname;
    private Date birthDay;
    private String login;
    private Float weight;
    private boolean isDeleted;
    private Timestamp created;
    private Timestamp changed;

    public  User() {
    }

    public User(Long id, String name, String surname, Date birthDay,
                String login, Float weight, boolean isDeleted,
                Timestamp created, Timestamp changed) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDay = birthDay;
        this.login = login;
        this.weight = weight;
        this.isDeleted = isDeleted;
        this.created = created;
        this.changed = changed;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isDeleted == user.isDeleted
                && Objects.equals(id, user.id)
                && Objects.equals(name, user.name)
                && Objects.equals(surname, user.surname)
                && Objects.equals(birthDay, user.birthDay)
                && Objects.equals(login, user.login)
                && Objects.equals(weight, user.weight)
                && Objects.equals(created, user.created)
                && Objects.equals(changed, user.changed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, birthDay, login,
                weight, isDeleted, created, changed);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.JSON_STYLE);
    }
}
