package br.com.peopleservice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table
public class Birthday {

    @Id
    @Column(name = "birthday_id")
    private UUID birthdayId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer day;

    @Column(nullable = false)
    private Integer month;

    public Birthday() {}

    public Birthday(UUID nextPeopleId, String name, Integer day, Integer month) {
        setBirthdayId(nextPeopleId);
        setName(name);
        setDay(day);
        setMonth(month);
    }

    public UUID getBirthdayId() {
        return birthdayId;
    }

    public void setBirthdayId(UUID birthdayId) {
        this.birthdayId = birthdayId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}
