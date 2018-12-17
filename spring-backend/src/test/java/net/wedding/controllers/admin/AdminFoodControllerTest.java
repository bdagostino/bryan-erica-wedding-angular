package net.wedding.controllers.admin;

import net.wedding.dto.admin.food.FoodAdminDto;
import net.wedding.entity.FoodEntity;
import net.wedding.services.admin.FoodService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@RunWith(SpringRunner.class)
@DataJpaTest(showSql = false, properties = {"logging.level.org.hibernate=ERROR"})
@Import({AdminFoodController.class, FoodService.class})
public class AdminFoodControllerTest {

    @Autowired
    private AdminFoodController adminFoodController;

    @Autowired
    private TestEntityManager testEntityManager;

    private FoodAdminDto foodAdminDtoSteak;
    private FoodAdminDto foodAdminDtoChicken;

    @Before
    public void setup() {
        final MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        this.foodAdminDtoChicken = new FoodAdminDto();
        this.foodAdminDtoSteak = new FoodAdminDto();
        this.foodAdminDtoChicken.setType("Chicken");
        this.foodAdminDtoChicken.setDescription("Fried Chicken");
        this.foodAdminDtoSteak.setType("Steak");
        this.foodAdminDtoSteak.setDescription("Ribeye Steak");
    }

    @Test
    public void testCreateFood() {
        final ResponseEntity responseEntity = this.adminFoodController.createFood(this.foodAdminDtoSteak);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        HttpHeaders headers = responseEntity.getHeaders();
        if (headers.getLocation() == null) {
            fail("Location Header cannot be null");
        }
        String id = StringUtils.substringAfterLast(headers.getLocation().toString(), "/");
        final FoodEntity foodEntity = this.testEntityManager.find(FoodEntity.class, Integer.parseInt(id));
        assertThat(foodEntity).isNotNull();
        assertThat(foodEntity.getType()).isEqualTo(this.foodAdminDtoSteak.getType());
        assertThat(foodEntity.getDescription()).isEqualTo(this.foodAdminDtoSteak.getDescription());
    }

    @Test
    public void testCreateFoodError() {
        FoodAdminDto foodAdminDto = new FoodAdminDto();
        ResponseEntity responseEntity = this.adminFoodController.createFood(foodAdminDto);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }


    @Test
    public void testRetrieveAllFoods() {
        this.adminFoodController.createFood(foodAdminDtoChicken);
        this.adminFoodController.createFood(foodAdminDtoSteak);

        final ResponseEntity<List<FoodEntity>> responseEntity = this.adminFoodController.retrieveAllFoods();
        final List<FoodEntity> foodEntities = responseEntity.getBody();
        assertThat(foodEntities).hasSize(2);
    }

    @Test
    public void testRetrieveFoodById() {
        this.adminFoodController.createFood(foodAdminDtoChicken);
        final ResponseEntity createResponseEntity = this.adminFoodController.createFood(foodAdminDtoSteak);
        final URI locationHeader = createResponseEntity.getHeaders().getLocation();
        final String id = StringUtils.substringAfterLast(locationHeader.toString(), "/");
        final ResponseEntity<FoodEntity> responseEntity = this.adminFoodController.retrieveFoodById(Integer.parseInt(id));
        final FoodEntity foodEntity = responseEntity.getBody();
        assertThat(foodEntity.getType()).isEqualTo(foodAdminDtoSteak.getType());
        assertThat(foodEntity.getDescription()).isEqualTo(foodAdminDtoSteak.getDescription());
    }

    @Test
    public void testDeleteFood() {
        final ResponseEntity createResponseEntity = this.adminFoodController.createFood(foodAdminDtoSteak);
        final URI locationHeader = createResponseEntity.getHeaders().getLocation();
        final String id = StringUtils.substringAfterLast(locationHeader.toString(), "/");
        final ResponseEntity responseEntity = this.adminFoodController.deleteFood(Integer.parseInt(id));
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }


    @Test
    public void testValidUpdateFood() {
        final ResponseEntity createResponseEntity = this.adminFoodController.createFood(foodAdminDtoSteak);
        final URI locationHeader = createResponseEntity.getHeaders().getLocation();
        final String id = StringUtils.substringAfterLast(locationHeader.toString(), "/");

        FoodAdminDto updateFoodDto = new FoodAdminDto();
        updateFoodDto.setType("Hello");
        updateFoodDto.setDescription("Hello World");
        final ResponseEntity responseEntity = this.adminFoodController.updateFood(Integer.parseInt(id), updateFoodDto);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        final FoodEntity updatedFoodEntity = this.testEntityManager.find(FoodEntity.class, Integer.parseInt(id));
        assertThat(updatedFoodEntity.getType()).isEqualTo("Hello");
        assertThat(updatedFoodEntity.getDescription()).isEqualTo("Hello World");
    }
}
