package com.example.http.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "catsuser")
public class User {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @EqualsAndHashCode.Include
    private Integer uuid;
    private String username;
    private String password;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner catOwner;
    @ElementCollection
    @Enumerated(value = EnumType.STRING)
    private Set<Roles> roles;
}
