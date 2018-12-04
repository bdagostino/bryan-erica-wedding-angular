package net.wedding.controllers.admin;

import net.wedding.models.Food;
import net.wedding.models.dto.FoodDto;
import net.wedding.repositories.FoodRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping(value = "/admin")
public class AdminFoodController {
    private static final Logger logger = LogManager.getLogger(AdminFoodController.class);
    private FoodRepository foodRepository;
    private Validator validator;

    public AdminFoodController(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();

    }

    @GetMapping(value = "/foods", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Food>> retrieveAllFoods() {
        logger.info("Retrieving All Food");
        return ResponseEntity.ok(this.foodRepository.findAll());
    }

    @GetMapping(value = "/foods/{id}")
    public ResponseEntity<Food> retrieveFood(@PathVariable final Integer id) {
        logger.info("Retrieve Food for Id: " + id);
        final Optional<Food> food = foodRepository.findById(id);
        if (food.isPresent()) {
            logger.info("Food Id: " + id + " not found");
            return ResponseEntity.ok(food.get());
        } else {
            logger.info("Food Id: " + id + " not found");
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
    }

    @DeleteMapping(value = "/foods/{id}")
    public void deleteFood(@PathVariable final Integer id) {
        logger.info("Delete Object with Id " + id);
        foodRepository.deleteById(id);
    }

    @PostMapping(value = "/foods", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Void> createFood(@RequestBody final FoodDto foodDto) {
        logger.info("Creating New Food");
        final Food foodEntity = new Food();
        mapDtoToFood(foodDto, foodEntity);
        final Set<ConstraintViolation<Food>> violations = validator.validate(foodEntity);
        if (violations.isEmpty()) {
            foodRepository.save(foodEntity);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/foods/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateFood(@PathVariable final Integer id, @RequestBody final FoodDto foodDto) {
        logger.info("Update Food for Id: " + id);
        final Optional<Food> optionalFood = foodRepository.findById(id);
        if (optionalFood.isPresent()) {
            final Food foodEntity = optionalFood.get();
            mapDtoToFood(foodDto, foodEntity);
            final Set<ConstraintViolation<Food>> violations = validator.validate(foodEntity);
            if (violations.isEmpty()) {
                foodRepository.save(foodEntity);
                logger.info("Update Food for Id: " + id + " Saved");
                return ResponseEntity.ok().build();
            }
        }
        logger.info("Food Id: " + id + " not found");
        return ResponseEntity.badRequest().build();
    }

    private void mapDtoToFood(final FoodDto foodDto, final Food foodEntity) {
        foodEntity.setType(StringUtils.trim(foodDto.getType()));
        foodEntity.setDescription(StringUtils.trim(foodDto.getDescription()));
    }
}
