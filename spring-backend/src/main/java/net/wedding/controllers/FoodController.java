package net.wedding.controllers;

import net.wedding.entity.FoodEntity;
import net.wedding.repositories.FoodRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@CrossOrigin
@RestController
public class FoodController {

    private FoodRepository foodRepository;

    public FoodController(final FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @GetMapping(value = "/foods")
    public ResponseEntity<List<FoodEntity>> retrieveAllFood() {
        final List<FoodEntity> foodEntityList = this.foodRepository.findAll();
        if (!foodEntityList.isEmpty()) {
            return ResponseEntity.ok(foodEntityList);
        }
        return ResponseEntity.notFound().location(ServletUriComponentsBuilder.fromCurrentRequest().build().toUri()).build();
    }
}
