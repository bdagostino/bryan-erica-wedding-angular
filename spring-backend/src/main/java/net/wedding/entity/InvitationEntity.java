package net.wedding.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @JsonManagedReference
    private List<GuestEntity> guestList = new ArrayList<>();

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

    public List<GuestEntity> getGuestList() {
        return guestList;
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

    public void addGuest(final GuestEntity guestEntity) {
        this.guestList.add(guestEntity);
        guestEntity.setInvitationEntity(this);
    }

    public void removeGuest(final GuestEntity guestEntity) {
        this.guestList.remove(guestEntity);
        guestEntity.setInvitationEntity(null);
    }
}