package net.wedding.repositories;


import net.wedding.entity.InvitationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvitationRepository extends PagingAndSortingRepository<InvitationEntity, Integer> {

//    @Query(value = "select distinct i from InvitationEntity i join i.guestList l where l.firstName like :guestName% or l.lastName like :guestName%")
//    Page<InvitationEntity> findByGuestName(@Param("guestName") String guestName, Pageable pageable);
//
//    @Query(value = "select distinct i from InvitationEntity i join i.guestList l where (l.firstName like :firstName% and l.lastName like :lastName%) or (l.firstName like :lastName% and l.lastName like :firstName%)")
//    Page<InvitationEntity> findByFullName(@Param("firstName") String firstName, @Param("lastName") String lastName, Pageable pageable);

    InvitationEntity findByInvitationCode(final String invitationCode);

//    boolean existsByInvitationCode(final String invitationCode);

    List<InvitationEntity> findAll();
}
