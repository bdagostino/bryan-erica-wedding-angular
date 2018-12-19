package net.wedding.services.admin;

import net.wedding.dto.admin.invitation.CreateInvitationDto;
import net.wedding.entity.GuestEntity;
import net.wedding.entity.InvitationEntity;
import net.wedding.repositories.InvitationRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InvitationService {

    private InvitationRepository invitationRepository;

    public InvitationService(final InvitationRepository invitationRepository) {
        this.invitationRepository = invitationRepository;
    }

    public boolean validateCreateInvitationDto(final CreateInvitationDto createInvitationDto) {
        final int maxGuestCount = createInvitationDto.getMaxGuestCount();
        final int guestListSize = createInvitationDto.getGuestList().size();
        return (maxGuestCount > 0 && guestListSize <= maxGuestCount);
    }

    public InvitationEntity createInvitationEntityFromCreateInvitationDto(final CreateInvitationDto createInvitationDto) {
        final InvitationEntity invitationEntity = new InvitationEntity();
        invitationEntity.setMaxGuests(createInvitationDto.getMaxGuestCount());
        createInvitationDto.getGuestList().forEach(createGuestDto -> {
            final GuestEntity guestEntity = new GuestEntity();
            guestEntity.setFirstName(createGuestDto.getFirstName());
            guestEntity.setLastName(createGuestDto.getLastName());
            guestEntity.setAdditionalGuest(false);
            invitationEntity.addGuest(guestEntity);
        });
        return invitationEntity;
    }

    /**
     * Generates an invitation code and verifies that it does not exist.
     * DOES NOT GUARANTEE uniqueness of the invitation code when invitation is being saved
     *
     * @return generated invitation code
     */
    public String generateInvitationCode() {
        Optional<InvitationEntity> invitationEntity;
        String invitationCode;
        do {
            invitationCode = RandomStringUtils.randomAlphabetic(4).toUpperCase();
            invitationEntity = invitationRepository.findByInvitationCode(invitationCode);
        } while (invitationEntity.isPresent());
        return invitationCode;
    }
}
