package com.yodishtr.smart_city_guide.Repository;

import com.yodishtr.smart_city_guide.Entities.City;
import com.yodishtr.smart_city_guide.Entities.Place;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlacesRepository extends JpaRepository<Place, Long> {

    List<Place> findByName(String name);
    List<Place> findByAddress(String address);
    List<Place> findByAddressContainingIgnoreCase(String address);
    List<Place> findByCategory(Place.CATEGORY category);
    List<Place> findByCity(City city);

    @EntityGraph(attributePaths = {"city"})
    Optional<Place> findByNameAndCategory(String name, Place.CATEGORY category);
}
