package net.wedding.repositories;


import net.wedding.entity.FoodEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface FoodRepository extends PagingAndSortingRepository<FoodEntity, Integer> {

    List<FoodEntity> findAll();

    Page<FoodEntity> findByTypeStartingWith(String type, Pageable pageable);

}
