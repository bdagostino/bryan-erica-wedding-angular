package net.wedding.controllers.admin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.wedding.models.Food;
import net.wedding.models.dto.FoodDto;
import net.wedding.repositories.FoodRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("local")
public class AdminFoodControllerTest {

    @Autowired
    AdminFoodController adminFoodController;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Autowired
    FoodRepository foodRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .alwaysDo(print())
//                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void testRetrieveAllFoods() throws Exception {
        this.mockMvc.perform(get("/admin/foods")).andExpect(status().is(200)).andDo(mvcResult -> {
            final List<Food> foodList = this.objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Food>>() {
            });
            assertThat(foodList).isNotEmpty().hasSize(2);
        });
    }

    @Test
    public void testRetrieveFood() throws Exception {
        this.mockMvc.perform(get("/admin/foods/1")).andExpect(status().is(200)).andDo(mvcResult -> {
            final Food food = this.objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Food.class);
            assertThat(food.getId()).isEqualTo(1);
            assertThat(food.getType()).isEqualTo("Chicken");
            assertThat(food.getDescription()).isEqualTo("Grilled Chicken");
        });
    }

    @Test
    @Ignore
    public void testDeleteFood() throws Exception {
        this.mockMvc.perform(delete("/admin/foods/2"));
        this.mockMvc.perform(get("/admin/foods")).andExpect(status().is(200)).andDo(mvcResult -> {
            final List<Food> foodList = this.objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Food>>() {
            });
            assertThat(foodList).isNotEmpty().hasSize(1);
        });
    }

    @Test
    public void testCreateFood() throws Exception {
        Food food = new Food();
        food.setType("Fish");
        food.setDescription("Salmon");
        this.mockMvc.perform(post("/admin/foods").content(objectMapper.writeValueAsString(food))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(200)).andDo(mvcResult -> {
            assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo("http://localhost/admin/foods/100");
        });
    }

    @Test
    public void testCreateFoodError() throws Exception {
        Food food = new Food();
        food.setDescription("Salmon");
        this.mockMvc.perform(post("/admin/foods").content(objectMapper.writeValueAsString(food))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(400));
    }

    @Test
    public void testValidUpdateFood() throws Exception {
        FoodDto foodDto = new FoodDto();
        foodDto.setType("Shrimp");
        foodDto.setDescription("Grilled Shrimp");
        this.mockMvc.perform(put("/admin/foods/1").content(objectMapper.writeValueAsString(foodDto))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(200));
    }

    @Test
    public void testMissingUpdateFood() throws Exception {
        FoodDto foodDto = new FoodDto();
        foodDto.setType("Shrimp");
        foodDto.setDescription("Grilled Shrimp");
        this.mockMvc.perform(put("/admin/foods/300").content(objectMapper.writeValueAsString(foodDto))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(400));
    }

    @Test
    public void testInvalidUpdateFood() throws Exception {
        FoodDto foodDto = new FoodDto();
        foodDto.setType("Shrimp");
        this.mockMvc.perform(put("/admin/foods/1").content(objectMapper.writeValueAsString(foodDto))
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(400));
    }
}
