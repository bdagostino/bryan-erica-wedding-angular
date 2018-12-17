package net.wedding.controllers;

import net.wedding.models.rsvp.RsvpSearchRequest;
import net.wedding.models.rsvp.RsvpSearchResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/rsvp")
public class RsvpController {


    @PostMapping(path = "/search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity<RsvpSearchResponse> formSubmit(@RequestBody RsvpSearchRequest rsvpSearch) throws Exception {
        System.out.println("Form Submitted");
        System.out.println("InvitationCode: " + rsvpSearch.getInvitationCode());

        // return ResponseEntity.ok(new RsvpResponse(false, "InvitationEntity Not Found"));
        return ResponseEntity.ok(new RsvpSearchResponse(true, "InvitationEntity Not Found"));
    }
}
