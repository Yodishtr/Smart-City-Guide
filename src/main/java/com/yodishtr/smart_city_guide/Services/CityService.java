package com.yodishtr.smart_city_guide.Services;

import com.yodishtr.smart_city_guide.Entities.City;
import com.yodishtr.smart_city_guide.Repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City getCityById(Long id) {
        return cityRepository.findById(id).orElse(null);
    }

    public City createCity(City city) {
        return cityRepository.save(city);
    }

    public City updateCity(City city) {
        return cityRepository.save(city);
    }

    public void deleteCity(Long id) {
        cityRepository.deleteById(id);
    }

    public List<City> partialSearchCity(String query) {
        return cityRepository.findByNameContainingIgnoreCase(query);
    }

}
