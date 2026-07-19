package com.yodishtr.smart_city_guide.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (
        name = "cities",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "country_city_name_unique",
                        columnNames = {"name", "country"}
                )
        }
)
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(nullable = false)
    private String country;

    private long population;

    // places
    @OneToMany(mappedBy = "city", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Place> places = new ArrayList<>();

    public City() {}

    public City(String name, String description, String country, long population) {
        this.name = name;
        this.description = description;
        this.country = country;
        this.population = population;
    }

    // getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCountry() {
        return country;
    }

    public Long getPopulation() {
        return population;
    }

    public List<Place> getPlaces() {
        return places;
    }

    // setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public void setPlaces(List<Place> places) {
        this.places = places;
    }

    public void addPlace(Place place) {
        if (places != null) {
            this.places.add(place);
            place.setCity(this);
        }
    }

    public void removePlace(Place place) {
        if (places != null) {
            this.places.remove(place);
            place.setCity(null);
        }
    }

}
