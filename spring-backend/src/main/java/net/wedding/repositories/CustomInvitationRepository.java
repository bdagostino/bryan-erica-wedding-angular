package net.wedding.repositories;


import net.wedding.models.Invitation;

public interface CustomInvitationRepository {

    boolean saveInvitation(Invitation invitation);
}
