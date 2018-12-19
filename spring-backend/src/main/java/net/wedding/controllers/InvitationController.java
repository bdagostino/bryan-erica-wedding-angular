package net.wedding.controllers;

import net.wedding.controllers.admin.AdminInvitationController;
import net.wedding.entity.InvitationEntity;
import net.wedding.repositories.InvitationRepository;
import net.wedding.services.admin.InvitationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

@CrossOrigin
@RestController
public class InvitationController {

    private static final Logger logger = LogManager.getLogger(InvitationController.class);
    private InvitationRepository invitationRepository;
    private InvitationService invitationService;

    public InvitationController(final InvitationRepository invitationRepository, final InvitationService invitationService) {
        this.invitationRepository = invitationRepository;
        this.invitationService = invitationService;
    }

    @GetMapping(value = "/invitations/{invitationCode}")
    public ResponseEntity<InvitationEntity> getInvitationByInvitationCode(@PathVariable final String invitationCode){
        final Optional<InvitationEntity> optionalInvitationEntity = this.invitationRepository.findByInvitationCode(invitationCode);
        if (optionalInvitationEntity.isPresent()) {
            return ResponseEntity.ok(optionalInvitationEntity.get());
        }
        return ResponseEntity.notFound().location(ServletUriComponentsBuilder.fromCurrentRequest().build().toUri()).build();
    }
}
