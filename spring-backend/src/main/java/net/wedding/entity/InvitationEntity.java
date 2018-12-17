package net.wedding.entity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "invitation")
public class InvitationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Valid
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "invitationEntity", targetEntity = GuestEntity.class, orphanRemoval = true)
    @Column(name = "guest_list")
    private List<GuestEntity> guestEntityList = new ArrayList<>();

    @Column(name = "max_guests")
    @NotNull
    @Min(1)
    private Integer maxGuests;

    @Column(name = "invitation_code", unique = true)
    @NotNull
    private String invitationCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<GuestEntity> getGuestEntityList() {
        return guestEntityList;
    }

    public Integer getMaxGuests() {
        return maxGuests;
    }

    public void setMaxGuests(Integer maxGuests) {
        this.maxGuests = maxGuests;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public List<Integer> getInvitedGuestIndicies() {
        ArrayList<Integer> invitedGuestIndiciesList = new ArrayList<>();
        for (int index = 0; index < this.getGuestEntityList().size(); index++) {
            if (this.getGuestEntityList().get(index).getAdditionalGuest()) {
                invitedGuestIndiciesList.add(index);
            }
        }
        return invitedGuestIndiciesList;
    }

    public List<Integer> getAdditionalGuestIndicies() {
        ArrayList<Integer> additionalGuestIndiciesList = new ArrayList<>();
        for (int index = 0; index < this.getGuestEntityList().size(); index++) {
            if (!this.getGuestEntityList().get(index).getAdditionalGuest()) {
                additionalGuestIndiciesList.add(index);
            }
        }
        return additionalGuestIndiciesList;
    }

    public void addGuest(final GuestEntity guestEntity) {
        this.guestEntityList.add(guestEntity);
        guestEntity.setInvitationEntity(this);
    }

    public void removeGuest(final GuestEntity guestEntity) {
        this.guestEntityList.remove(guestEntity);
        guestEntity.setInvitationEntity(null);
    }
}