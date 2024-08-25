package org.example.models.cat;

import org.example.models.owner.Owner;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cats", schema = "public")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cats_id_gen")
    @SequenceGenerator(name = "cats_id_gen", sequenceName = "cats_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "name", nullable = false)
    private String name;

    @Lob
    @Column(name = "breed", nullable = false)
    private String breed;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;

    @Column(name = "color", nullable = false)
    private Color color;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "cats_friendship",
            joinColumns = {@JoinColumn(name = "first_cat_id")},
            inverseJoinColumns = {@JoinColumn(name = "second_cat_id")}
    )
    private List<Cat> friends;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}