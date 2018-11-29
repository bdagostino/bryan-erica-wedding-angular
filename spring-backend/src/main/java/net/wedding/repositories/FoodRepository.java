package net.wedding.repositories;


import net.wedding.models.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface FoodRepository extends PagingAndSortingRepository<Food, Integer> {

    List<Food> findAll();

    Page<Food> findByTypeStartingWith(String type, Pageable pageable);

}
