package net.wedding.repositories;


import net.wedding.models.Guest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GuestRepository extends PagingAndSortingRepository<Guest, Integer> {

    Page<Guest> findByFirstNameStartingWithOrLastNameStartingWith(String firstName, String lastName, Pageable pageable);

    Page<Guest> findByFirstNameStartingWithAndLastNameStartingWithOrFirstNameStartingWithAndLastNameStartingWith(String firstName1, String lastName1, String lastName2, String firstName2, Pageable pageable);
}
