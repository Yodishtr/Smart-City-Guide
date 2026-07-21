package com.yodishtr.smart_city_guide.Services;

import com.yodishtr.smart_city_guide.Entities.City;
import com.yodishtr.smart_city_guide.Entities.Place;
import com.yodishtr.smart_city_guide.Repository.CityRepository;
import com.yodishtr.smart_city_guide.Repository.PlacesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class PlaceService {
    private final PlacesRepository placesRepository;
    private final CityRepository cityRepository;

    public PlaceService(PlacesRepository placesRepository, CityRepository cityRepository) {
        this.placesRepository = placesRepository;
        this.cityRepository = cityRepository;
    }

    public List<Place> getPlacesByCity(String city) {
        List<City> cityList = cityRepository.findByName(city);
        HashSet<Place> placesSet = new HashSet<>();
        for (City city1 : cityList) {
           List<Place> placeList = placesRepository.findByCity(city1);
           for (Place place : placeList) {
               placesSet.add(place);
           }
        }
        return new ArrayList<>(placesSet);
    }

    public List<Place> getPlacesByCategory(String category) {
        Place.CATEGORY currentCategory = Place.CATEGORY.fromValue(category);
        ArrayList<Place> placesList = new ArrayList<>();
        for (Place place : placesRepository.findByCategory(currentCategory)) {
            placesList.add(place);
        }
        return placesList;
    }

    public List<Place> getPlacesByName(String name) {
        ArrayList<Place> placesList = new ArrayList<>();
        for (Place place : placesRepository.findByName(name)) {
            placesList.add(place);
        }
        return placesList;
    }
}
