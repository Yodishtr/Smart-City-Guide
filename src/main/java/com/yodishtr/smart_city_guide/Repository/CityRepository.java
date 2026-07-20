package com.yodishtr.smart_city_guide.Repository;

import com.yodishtr.smart_city_guide.Entities.City;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {


    List<City> findByName(String name);
    List<City> findByCountry(String countryName);
    List<City> findByPopulation(long population);
    List<City> findByNameContainingIgnoreCase(String partialName);
    List<City> findByNameContainingIgnoreCaseAndCountry(String partialName, String countryName);
    List<City> findByNameContainingIgnoreCaseAndPopulation(String partialName, long population);

    @EntityGraph(attributePaths = {"places"})
    Optional<City> findByNameAndCountry(String name, String countryName);

}
