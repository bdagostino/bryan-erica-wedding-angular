package net.wedding.services.admin;

import net.wedding.dto.admin.food.FoodAdminDto;
import net.wedding.entity.FoodEntity;
import org.springframework.stereotype.Component;

@Component
public class FoodService {

    public FoodEntity createFoodEntityFromCreateFoodDto(final FoodAdminDto foodAdminDto) {
        final FoodEntity foodEntity = new FoodEntity();
        foodEntity.setType(foodAdminDto.getType());
        foodEntity.setDescription(foodAdminDto.getDescription());
        return foodEntity;
    }

    public void updateFoodEntityFromUpdateFoodDto(final FoodAdminDto foodAdminDto, final FoodEntity foodEntity) {
        foodEntity.setType(foodAdminDto.getType());
        foodEntity.setDescription(foodAdminDto.getDescription());
    }
}
