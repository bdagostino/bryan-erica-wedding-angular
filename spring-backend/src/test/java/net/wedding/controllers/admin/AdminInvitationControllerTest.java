package net.wedding.controllers.admin;

import net.wedding.dto.admin.invitation.CreateGuestDto;
import net.wedding.dto.admin.invitation.CreateInvitationDto;
import net.wedding.entity.GuestEntity;
import net.wedding.entity.InvitationEntity;
import net.wedding.services.admin.InvitationService;
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
@Import(value = {InvitationService.class, AdminInvitationController.class})
public class AdminInvitationControllerTest {

    @Autowired
    private AdminInvitationController adminInvitationController;

    @Autowired
    private TestEntityManager testEntityManager;

    private CreateGuestDto createGuestDtoHello;

    private CreateGuestDto createGuestDtoSunny;

    @Before
    public void setup() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        this.createGuestDtoHello = new CreateGuestDto();
        this.createGuestDtoSunny = new CreateGuestDto();
        this.createGuestDtoHello.setFirstName("Hello");
        this.createGuestDtoHello.setLastName("World");
        this.createGuestDtoSunny.setFirstName("Sunny");
        this.createGuestDtoSunny.setLastName("Day");
    }

    @Test
    public void testCreateInvitationOneGuest() {
        final CreateInvitationDto createInvitationDto = new CreateInvitationDto();
        createInvitationDto.setMaxGuestCount(3);
        createInvitationDto.getGuestList().add(createGuestDtoHello);

        ResponseEntity responseEntity = this.adminInvitationController.createInvitation(createInvitationDto);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        HttpHeaders headers = responseEntity.getHeaders();
        if (headers.getLocation() == null) {
            fail("Location Header cannot be null");
        }
        String id = StringUtils.substringAfterLast(headers.getLocation().toString(), "/");
        InvitationEntity invitationEntity = testEntityManager.find(InvitationEntity.class, Integer.parseInt(id));
        assertThat(invitationEntity).isNotNull();
        assertThat(invitationEntity.getMaxGuests()).isEqualTo(createInvitationDto.getMaxGuestCount());
        assertThat(invitationEntity.getGuestEntityList()).hasSize(1);
        GuestEntity guestEntity = invitationEntity.getGuestEntityList().get(0);
        assertThat(guestEntity.getFirstName()).isEqualTo(createGuestDtoHello.getFirstName());
        assertThat(guestEntity.getLastName()).isEqualTo(createGuestDtoHello.getLastName());
        assertThat(guestEntity.getAdditionalGuest()).isFalse();
    }

    @Test
    public void testCreateInvitationTwoGuest() {
        final CreateInvitationDto createInvitationDto = new CreateInvitationDto();
        createInvitationDto.setMaxGuestCount(2);
        createInvitationDto.getGuestList().add(createGuestDtoHello);
        createInvitationDto.getGuestList().add(createGuestDtoSunny);

        ResponseEntity responseEntity = this.adminInvitationController.createInvitation(createInvitationDto);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        HttpHeaders headers = responseEntity.getHeaders();
        if (headers.getLocation() == null) {
            fail("Location Header cannot be null");
        }
        String id = StringUtils.substringAfterLast(headers.getLocation().toString(), "/");
        InvitationEntity invitationEntity = testEntityManager.find(InvitationEntity.class, Integer.parseInt(id));
        assertThat(invitationEntity).isNotNull();
        assertThat(invitationEntity.getMaxGuests()).isEqualTo(createInvitationDto.getMaxGuestCount());
        assertThat(invitationEntity.getGuestEntityList()).hasSize(2);
        final GuestEntity guestEntity1 = invitationEntity.getGuestEntityList().get(0);
        assertThat(guestEntity1.getFirstName()).isEqualTo(createGuestDtoHello.getFirstName());
        assertThat(guestEntity1.getLastName()).isEqualTo(createGuestDtoHello.getLastName());
        assertThat(guestEntity1.getAdditionalGuest()).isFalse();
        final GuestEntity guestEntity2 = invitationEntity.getGuestEntityList().get(1);
        assertThat(guestEntity2.getFirstName()).isEqualTo(createGuestDtoSunny.getFirstName());
        assertThat(guestEntity2.getLastName()).isEqualTo(createGuestDtoSunny.getLastName());
        assertThat(guestEntity2.getAdditionalGuest()).isFalse();
    }

    @Test
    public void testCreateInvitationMoreThanMaxGuestCount() {
        final CreateInvitationDto createInvitationDto = new CreateInvitationDto();
        createInvitationDto.setMaxGuestCount(1);
        createInvitationDto.getGuestList().add(createGuestDtoHello);
        createInvitationDto.getGuestList().add(createGuestDtoSunny);

        ResponseEntity responseEntity = this.adminInvitationController.createInvitation(createInvitationDto);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testRetrieveNoInvitations() {
        final ResponseEntity<List<InvitationEntity>> responseEntity = adminInvitationController.retrieveAllInvitations();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testRetrieveAllInvitations() {
        final CreateInvitationDto createInvitationDto1 = new CreateInvitationDto();
        createInvitationDto1.setMaxGuestCount(2);
        createInvitationDto1.getGuestList().add(createGuestDtoHello);
        createInvitationDto1.getGuestList().add(createGuestDtoSunny);

        final CreateInvitationDto createInvitationDto2 = new CreateInvitationDto();
        createInvitationDto2.setMaxGuestCount(4);
        createInvitationDto2.getGuestList().add(createGuestDtoSunny);
        createInvitationDto2.getGuestList().add(createGuestDtoHello);

        this.adminInvitationController.createInvitation(createInvitationDto1);
        this.adminInvitationController.createInvitation(createInvitationDto2);


        final ResponseEntity<List<InvitationEntity>> responseEntity = adminInvitationController.retrieveAllInvitations();
        final List<InvitationEntity> invitationEntityList = responseEntity.getBody();
        assertThat(invitationEntityList).hasSize(2);
    }

    @Test
    public void testRetrieveInvitationsByIdNotFound(){
        final Integer invitationId = 3;
        ResponseEntity responseEntity = adminInvitationController.retrieveInvitationsById(invitationId);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        final URI headerLocation = responseEntity.getHeaders().getLocation();
        assertThat(headerLocation).isNotNull();
    }

    @Test
    public void testRetrieveInvitationsById(){
        final CreateInvitationDto createInvitationDto1 = new CreateInvitationDto();
        createInvitationDto1.setMaxGuestCount(2);
        createInvitationDto1.getGuestList().add(createGuestDtoHello);
        createInvitationDto1.getGuestList().add(createGuestDtoSunny);

        final CreateInvitationDto createInvitationDto2 = new CreateInvitationDto();
        createInvitationDto2.setMaxGuestCount(4);
        createInvitationDto2.getGuestList().add(createGuestDtoSunny);
        createInvitationDto2.getGuestList().add(createGuestDtoHello);

        this.adminInvitationController.createInvitation(createInvitationDto1);
        ResponseEntity responseEntity = this.adminInvitationController.createInvitation(createInvitationDto2);
        final URI locationHeader = responseEntity.getHeaders().getLocation();
        String id = StringUtils.substringAfterLast(locationHeader.toString(), "/");

        final ResponseEntity<InvitationEntity> invitationResponseEntity = this.adminInvitationController.retrieveInvitationsById(Integer.parseInt(id));
        InvitationEntity invitationEntity = invitationResponseEntity.getBody();
        assertThat(invitationEntity.getMaxGuests()).isEqualTo(createInvitationDto2.getMaxGuestCount());
        assertThat(invitationEntity.getInvitationCode()).isNotBlank();
    }
}
