package com.yodishtr.smart_city_guide.Entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Check;

import java.util.HashMap;
import java.util.Map;

@Entity
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

}
