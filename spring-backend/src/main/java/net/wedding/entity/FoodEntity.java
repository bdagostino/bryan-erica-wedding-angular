package net.wedding.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "food")
public class FoodEntity {

    private static final int TYPE_LENGTH = 25;
    private static final int DESCRIPTION_LENGTH = 150;

    public FoodEntity(int id) {
        this.id = id;
        this.type = "";
        this.description = "";
    }

    public FoodEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "type", nullable = false, length = TYPE_LENGTH)
    @NotNull(message = "FoodEntity Type is Required")
    @Size(min = 1, max = TYPE_LENGTH)
    private String type;

    @Column(name = "description", nullable = false, length = DESCRIPTION_LENGTH)
    @NotNull(message = "FoodEntity Description is Required")
    @Size(min = 1, max = DESCRIPTION_LENGTH)
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
