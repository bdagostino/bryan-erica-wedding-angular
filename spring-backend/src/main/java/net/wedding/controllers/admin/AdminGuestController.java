package net.wedding.controllers.admin;

import net.wedding.entity.GuestEntity;
import net.wedding.repositories.GuestRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(value = "/admin")
public class AdminGuestController {
    private static final Logger logger = LogManager.getLogger(AdminGuestController.class);
    private GuestRepository guestRepository;

    public AdminGuestController(final GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @GetMapping(value = "/guests", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GuestEntity>> retrieveAllGuests() {
        final List<GuestEntity> guestEntities = this.guestRepository.findAll();
        if (!guestEntities.isEmpty()) {
            return ResponseEntity.ok(guestEntities);
        }
        return ResponseEntity.notFound().location(ServletUriComponentsBuilder.fromCurrentRequest().build().toUri()).build();
    }

    @GetMapping(value = "/guests/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GuestEntity> retrieveGuestById(@PathVariable final Integer id) {
        final Optional<GuestEntity> optionalGuestEntity = this.guestRepository.findById(id);
        if (optionalGuestEntity.isPresent()) {
            return ResponseEntity.ok(optionalGuestEntity.get());
        }
        return ResponseEntity.notFound().location(ServletUriComponentsBuilder.fromCurrentRequest().build().toUri()).build();
    }

//    @DeleteMapping(value = "/guests/{id}")
//    public ResponseEntity<Void> deleteGuestById(@PathVariable final Integer id) {
//        final URI requestedLocation = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
//        final Optional<GuestEntity> optionalGuestEntity = this.guestRepository.findById(id);
//        if (optionalGuestEntity.isPresent()) {
//            this.guestRepository.delete(optionalGuestEntity.get());
//            return ResponseEntity.noContent().location(requestedLocation).build();
//        }
//        return ResponseEntity.notFound().location(requestedLocation).build();
//    }
}
