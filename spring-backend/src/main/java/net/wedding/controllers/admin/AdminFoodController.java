package net.wedding.controllers.admin;

import net.wedding.dto.admin.food.FoodAdminDto;
import net.wedding.entity.FoodEntity;
import net.wedding.repositories.FoodRepository;
import net.wedding.services.admin.FoodService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping(value = "/admin")
public class AdminFoodController {
    private static final Logger logger = LogManager.getLogger(AdminFoodController.class);
    private FoodRepository foodRepository;
    private FoodService foodService;
    private Validator validator;

    public AdminFoodController(final FoodRepository foodRepository, final FoodService foodService) {
        this.foodRepository = foodRepository;
        this.foodService = foodService;
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @GetMapping(value = "/foods", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FoodEntity>> retrieveAllFoods() {
        logger.info("Retrieving All Food");
        final List<FoodEntity> foodEntityList = this.foodRepository.findAll();
        if (!foodEntityList.isEmpty()) {
            return ResponseEntity.ok(foodEntityList);
        }
        return ResponseEntity.notFound().location(ServletUriComponentsBuilder.fromCurrentRequest().build().toUri()).build();
    }

    @GetMapping(value = "/foods/{id}")
    public ResponseEntity<FoodEntity> retrieveFoodById(@PathVariable final Integer id) {
        logger.info("Retrieve FoodEntity for Id: " + id);
        final Optional<FoodEntity> optionalFoodEntity = this.foodRepository.findById(id);
        if (optionalFoodEntity.isPresent()) {
            return ResponseEntity.ok(optionalFoodEntity.get());
        } else {
            logger.info("FoodEntity Id: " + id + " not found");
            return ResponseEntity.notFound().location(ServletUriComponentsBuilder.fromCurrentRequest().build().toUri()).build();
        }
    }

    @DeleteMapping(value = "/foods/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable final Integer id) {
        logger.info("Delete Object with Id " + id);
        final URI requestedLocation = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        final Optional<FoodEntity> optionalFoodEntity = this.foodRepository.findById(id);
        if (optionalFoodEntity.isPresent()) {
            this.foodRepository.delete(optionalFoodEntity.get());
            return ResponseEntity.noContent().location(requestedLocation).build();
        }
        return ResponseEntity.notFound().location(requestedLocation).build();
    }

    @PostMapping(value = "/foods", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<Void> createFood(@RequestBody final FoodAdminDto foodAdminDto) {
        logger.info("Creating New FoodEntity");
        final FoodEntity foodEntity = this.foodService.createFoodEntityFromCreateFoodDto(foodAdminDto);
        final Set<ConstraintViolation<FoodEntity>> violations = this.validator.validate(foodEntity);
        if (violations.isEmpty()) {
            final FoodEntity savedFoodEntity = foodRepository.save(foodEntity);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(savedFoodEntity.getId()).toUri();
            return ResponseEntity.created(location).build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping(value = "/foods/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateFood(@PathVariable final Integer id, @RequestBody final FoodAdminDto foodAdminDto) {
        logger.info("Update FoodEntity for Id: " + id);
        final URI requestedLocation = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        final Optional<FoodEntity> optionalFood = this.foodRepository.findById(id);
        if (optionalFood.isPresent()) {
            final FoodEntity foodEntity = optionalFood.get();
            this.foodService.updateFoodEntityFromUpdateFoodDto(foodAdminDto, foodEntity);
            final Set<ConstraintViolation<FoodEntity>> violations = validator.validate(foodEntity);
            if (violations.isEmpty()) {
                this.foodRepository.save(foodEntity);
                logger.info("Update FoodEntity for Id: " + id + " Saved");
                return ResponseEntity.noContent().location(requestedLocation).build();
            }
        }
        logger.info("FoodEntity Id: " + id + " not found");
        return ResponseEntity.notFound().location(requestedLocation).build();
    }
}
