package com.hmsapp.controller;

import com.hmsapp.entity.Property;
import com.hmsapp.repository.PropertyRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {

    private final PropertyRepository propertyRepository;

    public PropertyController(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @PostMapping("/addProperty")
    public String addProperty(){
        //add property logic here
        return "Property added successfully";
    }
@GetMapping("/{searchParam}")
    public List<Property> searchProperty(
            @PathVariable String  searchParam){
        return propertyRepository.searchProperty(searchParam);
}

}
