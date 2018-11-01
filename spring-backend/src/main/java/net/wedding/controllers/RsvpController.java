package net.wedding.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path = "/rsvp")
public class RsvpController {


    @PostMapping(path = "/submitForm", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    RsvpResponse formSubmit(@RequestBody RsvpSearch rsvpSearch) throws Exception {
        System.out.println("Form Submitted");
        System.out.println("InvitationCode: " + rsvpSearch.getInvitationCode() + ", Error Message: " + rsvpSearch.getErrorMessage());

        return new RsvpResponse("Success");
    }

    static class RsvpSearch {
        public String invitationCode;
        public String errorMessage;

        public String getInvitationCode() {
            return invitationCode;
        }

        public void setInvitationCode(String invitationCode) {
            this.invitationCode = invitationCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }

    static class RsvpResponse {
        public String value;

        RsvpResponse(final String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
