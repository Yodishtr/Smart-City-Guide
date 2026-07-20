package com.yodishtr.smart_city_guide.Entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Check;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
@Table (
        name = "place",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "place_name_category_unique",
                        columnNames = {"name", "category"}
                )
        }
)
public class Place {

    public enum CATEGORY {
        HOTEL("hotel"),
        RESTAURANT("restaurant"),
        ATTRACTION("attraction"),
        MISCELLANEOUS("miscellaneous");

        private final String value;

        CATEGORY(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        private static Map<String, CATEGORY> lookupMap = new HashMap<>();

        static {
            for (CATEGORY category : CATEGORY.values()) {
                lookupMap.put(category.getValue(), category);
            }
        }

        public static CATEGORY fromValue(String value) {
            if (value == null || value.isEmpty()) {
                return MISCELLANEOUS;
            }
            String sanitizedValue = value.trim().toLowerCase();
            return lookupMap.get(sanitizedValue);
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CATEGORY category;

    @Check(constraints = "rating >= 0 AND rating <= 5")
    private int rating;

    private double latitude;
    private double longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    public Place() {}

    public Place(String name, String description, String address, CATEGORY category, int rating, double latitude,
                 double longitude) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.category = category;
        this.rating = rating;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public String getAddress() {
        return address;
    }

    public CATEGORY getCategory() {
        return category;
    }

    public int getRating() {
        return rating;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public City getCity() {
        return city;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCategory(CATEGORY category) {
        this.category = category;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setCity(City city) {
        this.city = city;
    }

    // equals and hashcode method to determine the equality of place objects

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return Objects.equals(name, place.getName()) &&
                Objects.equals(category, place.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category);
    }
}
