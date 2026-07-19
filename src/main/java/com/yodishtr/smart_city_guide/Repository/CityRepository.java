package com.yodishtr.smart_city_guide.Repository;

import com.yodishtr.smart_city_guide.Entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

}
