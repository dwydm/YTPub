package pl.wydmuch.ytpub.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.PRIVATE)
    private UUID id;
    private String email;
    private String password;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    private boolean userActive;
    @OneToMany(mappedBy = "mainAuthor", fetch = FetchType.EAGER)
    private Set<Topic> mainTopics;
    @ManyToMany(mappedBy = "coauthors", fetch = FetchType.EAGER)
    private Set<Topic> secondaryTopics;
    @OneToMany(mappedBy = "sourceAuthor")
    private Set<Source> sources;

}
