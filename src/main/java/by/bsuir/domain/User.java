package by.bsuir.domain;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.sql.Date;
import java.util.Objects;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

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

    private Cars userCar;

    @Autowired
    public User(Cars userCar) {
        this.userCar = userCar;
    }


    /*public User(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }*/

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.JSON_STYLE);
    }
}
