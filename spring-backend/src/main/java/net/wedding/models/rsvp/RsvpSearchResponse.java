package net.wedding.models.rsvp;

public class RsvpSearchResponse {

    public boolean invitationExists;
    public String errorMessage;

    public RsvpSearchResponse(final boolean invitationExists, final String errorMessage) {
        this.invitationExists = invitationExists;
        this.errorMessage = errorMessage;
    }

    public boolean isInvitationExists() {
        return invitationExists;
    }

    public void setInvitationExists(boolean invitationExists) {
        this.invitationExists = invitationExists;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
