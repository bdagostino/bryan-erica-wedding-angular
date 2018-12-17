package net.wedding.repositories;


import net.wedding.entity.FoodEntity;
import net.wedding.entity.GuestEntity;
import net.wedding.entity.InvitationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface GuestRepository extends PagingAndSortingRepository<GuestEntity, Integer> {

    Page<GuestEntity> findByFirstNameStartingWithOrLastNameStartingWith(String firstName, String lastName, Pageable pageable);

    Page<GuestEntity> findByFirstNameStartingWithAndLastNameStartingWithOrFirstNameStartingWithAndLastNameStartingWith(String firstName1, String lastName1, String lastName2, String firstName2, Pageable pageable);

    List<GuestEntity> findAll();
}
