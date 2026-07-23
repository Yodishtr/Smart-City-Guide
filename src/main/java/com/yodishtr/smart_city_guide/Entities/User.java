package com.yodishtr.smart_city_guide.Entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DialectOverride;

import java.time.LocalDateTime;
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

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ROLE role;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public User() {}

    public User(String username, String email, String firstName, String lastName, String password, ROLE role) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
    }

    public User(String username, String email, String password, ROLE role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // getters
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public ROLE getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
