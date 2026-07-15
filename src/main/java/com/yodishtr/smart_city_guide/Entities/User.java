package com.yodishtr.smart_city_guide.Entities;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "app_users")
public class User {

    public enum ROLE {
        ADMIN("admin"),
        REGULAR("regular"),
        UNKNOWN("unknown");

        private final String value;

        ROLE(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        private static Map<String, ROLE> lookup = new HashMap<>();

        static {
            for (ROLE role : ROLE.values()) {
                lookup.put(role.getValue(), role);
            }
        }

        public static ROLE getRole(String input) {
            if (input == null || input.isEmpty()){
                return ROLE.UNKNOWN;
            }
            String sanitizedInput = input.trim().toLowerCase();
            return lookup.getOrDefault(sanitizedInput, ROLE.UNKNOWN);
        }

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;


}
