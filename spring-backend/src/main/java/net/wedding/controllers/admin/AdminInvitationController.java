package net.wedding.controllers.admin;

import net.wedding.dto.admin.invitation.CreateInvitationDto;
import net.wedding.entity.InvitationEntity;
import net.wedding.repositories.InvitationRepository;
import net.wedding.services.admin.InvitationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/admin")
public class AdminInvitationController {
    private static final Logger logger = LogManager.getLogger(AdminInvitationController.class);
    private InvitationRepository invitationRepository;
    private InvitationService invitationService;

    public AdminInvitationController(final InvitationRepository invitationRepository, final InvitationService invitationService) {
        this.invitationRepository = invitationRepository;
        this.invitationService = invitationService;
    }

    @GetMapping(value = "/invitations", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<InvitationEntity>> retrieveAllInvitations() {
        logger.info("Retrieving All Invitations");
        final List<InvitationEntity> invitationEntityList = this.invitationRepository.findAll();
        if (!invitationEntityList.isEmpty()) {
            return ResponseEntity.ok(invitationEntityList);
        }
        return ResponseEntity.notFound().location(ServletUriComponentsBuilder.fromCurrentRequest().build().toUri()).build();
    }

    @GetMapping(value = "/invitations/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InvitationEntity> retrieveInvitationsById(@PathVariable final Integer id) {
        final Optional<InvitationEntity> optionalInvitationEntity = this.invitationRepository.findById(id);
        if (optionalInvitationEntity.isPresent()) {
            return ResponseEntity.ok(optionalInvitationEntity.get());
        }
        return ResponseEntity.notFound().location(ServletUriComponentsBuilder.fromCurrentRequest().build().toUri()).build();
    }

    @DeleteMapping(value = "/invitations/{id}")
    public ResponseEntity<Void> deleteInvitationById(@PathVariable final Integer id) {
        logger.info("Delete InvitationEntity with Id " + id);
        final URI requestedLocation = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        final Optional<InvitationEntity> optionalInvitationEntity = this.invitationRepository.findById(id);
        if (optionalInvitationEntity.isPresent()) {
            this.invitationRepository.delete(optionalInvitationEntity.get());
            return ResponseEntity.noContent().location(requestedLocation).build();
        }
        return ResponseEntity.notFound().location(requestedLocation).build();
    }

    @PostMapping(value = "/invitations", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createInvitation(@RequestBody final CreateInvitationDto invitationDto) {
        logger.info("Creating InvitationEntity");

        final boolean isValid = this.invitationService.validateCreateInvitationDto(invitationDto);
        if (isValid) {
            //perform create
            final InvitationEntity invitationEntity = this.invitationService.createInvitationEntityFromCreateInvitationDto(invitationDto);
            invitationEntity.setInvitationCode(this.invitationService.generateInvitationCode());
            final InvitationEntity savedInvitationEntity = this.invitationRepository.save(invitationEntity);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(savedInvitationEntity.getId()).toUri();

            return ResponseEntity.created(location).build();
        } else {
            //bad request
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping(value = "/invitations/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InvitationEntity> updateInvitation(@PathVariable final Integer id) {
        //TODO Implement
        return null;
    }
}
