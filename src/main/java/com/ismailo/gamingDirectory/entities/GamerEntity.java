package com.ismailo.gamingDirectory.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

/*
    This class represents the gamers table.
    Each gamer has a unique id and a country String value
 */
@Entity
@Table(name = "gamers")
public class GamerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Country is required")
    private String country;

    /**
     * Empty constructor since it is required by JPA
     */
    public GamerEntity() {

    }

    public GamerEntity(String country) {
        this.country = country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GamerEntity)) return false;
        GamerEntity that = (GamerEntity) o;
        return Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country);
    }
}
