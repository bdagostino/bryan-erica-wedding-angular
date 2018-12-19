package net.wedding.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "guest")
public class GuestEntity {

    private static final int FIRST_NAME_LENGTH = 40;
    private static final int LAST_NAME_LENGTH = 40;

    public GuestEntity() {

    }

    public GuestEntity(final int id, final String firstName, final String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "first_name", nullable = false, length = FIRST_NAME_LENGTH)
    @NotNull
    @Size(min = 1, max = FIRST_NAME_LENGTH)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = LAST_NAME_LENGTH)
    @NotNull
    @Size(min = 1, max = LAST_NAME_LENGTH)
    private String lastName;

    @Column(name = "ceremony_attendance")
    private Boolean ceremonyAttendance;

    @Column(name = "reception_attendance")
    private Boolean receptionAttendance;

    @ManyToOne(targetEntity = FoodEntity.class)
    @JoinColumn(name = "food_id")
    private FoodEntity food;

    @Column(name = "dietary_concerns")
    private Boolean dietaryConcerns;

    @Column(name = "dietary_comments")
    private String dietaryComments;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = InvitationEntity.class)
    @JoinColumn(name = "invite_id", nullable = false)
    @JsonBackReference
    private InvitationEntity invitationEntity;

    @Column(name = "additional_guest")
    @NotNull
    private Boolean additionalGuest;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public Boolean getAdditionalGuest() {
        return additionalGuest;
    }

    public void setAdditionalGuest(Boolean additionalGuest) {
        this.additionalGuest = additionalGuest;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.trim();
    }

    public FoodEntity getFood() {
        return food;
    }

    public void setFood(FoodEntity food) {
        this.food = food;
    }

    public Boolean getDietaryConcerns() {
        return dietaryConcerns;
    }

    public void setDietaryConcerns(Boolean dietaryConcerns) {
        this.dietaryConcerns = dietaryConcerns;
    }

    public String getDietaryComments() {
        return dietaryComments;
    }

    public void setDietaryComments(String dietaryComments) {
        this.dietaryComments = dietaryComments;
    }

    public InvitationEntity getInvitationEntity() {
        return invitationEntity;
    }

    public void setInvitationEntity(InvitationEntity invitationEntity) {
        this.invitationEntity = invitationEntity;
    }

    public Boolean getCeremonyAttendance() {
        return ceremonyAttendance;
    }

    public void setCeremonyAttendance(Boolean ceremonyAttendance) {
        this.ceremonyAttendance = ceremonyAttendance;
    }

    public Boolean getReceptionAttendance() {
        return receptionAttendance;
    }

    public void setReceptionAttendance(Boolean receptionAttendance) {
        this.receptionAttendance = receptionAttendance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GuestEntity)) {
            return false;
        }
        return this.id != null && this.id.equals(((GuestEntity) o).id);
    }
}
