package com.example.cat.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "cats", schema = "public")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Setter(AccessLevel.NONE)
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

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @ManyToMany(targetEntity = Cat.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "cats_friendship",
            joinColumns = {@JoinColumn(name = "first_cat_id")},
            inverseJoinColumns = {@JoinColumn(name = "second_cat_id")}
    )
    private List<Cat> friends;

    public Cat(String name, LocalDate birthday, String breed, Color color) {
        this.name = name;
        this.birthday = birthday;
        this.breed = breed;
        this.color = color;
        this.friends = new ArrayList<>();
    }

    public void addFriend(Cat cat) {
        if (cat != null && !friends.contains(cat)) {
            friends.add(cat);
            cat.addFriend(this);
        }
    }

    public void removeFriend(Cat cat) {
        if (cat != null && friends.contains(cat)) {
            friends.remove(cat);
            cat.removeFriend(this);
        }
    }

    public List<Cat> getFriends() {
        return Collections.unmodifiableList(friends);
    }
}