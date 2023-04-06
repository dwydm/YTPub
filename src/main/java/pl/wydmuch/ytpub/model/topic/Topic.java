package pl.wydmuch.ytpub.model.topic;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import pl.wydmuch.ytpub.model.source.Source;
import pl.wydmuch.ytpub.model.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "topics")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.PRIVATE)
    private UUID id;
    private String title;
    private String description;
    @Setter(AccessLevel.PRIVATE)
    private String topicSerialNumber;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy/MM/dd")
    private LocalDate creationDate;
    @Enumerated(EnumType.STRING)
    private CoauthorStatus coauthorStatus;
    @Enumerated(EnumType.STRING)
    private PublicationStatus publicationStatus;
    @JsonBackReference
    @ManyToOne
    private User mainAuthor;
    @ManyToMany
    @JoinTable(name = "coauthor_topic", joinColumns = @JoinColumn(name = "topic_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> coauthors;
    @OneToMany(mappedBy = "topic")
    private Set<Source> sources;

    @PrePersist
    private void generateTitleSerialNumberAndDate() {
        LocalDateTime creationDate = LocalDateTime.now();
        this.creationDate = creationDate.toLocalDate();

        String year = DateTimeFormatter.ofPattern("yy").format(creationDate);
        String month = DateTimeFormatter.ofPattern("MM").format(creationDate);
        String day = DateTimeFormatter.ofPattern("dd").format(creationDate);
        String seconds = DateTimeFormatter.ofPattern("ss").format(creationDate);
        topicSerialNumber = String.format("%c%s%s%s%s%c%d%d",
                new Random().nextInt('A', 'Z'),
                year, month, day, seconds,
                new Random().nextInt('A', 'Z'),
                new Random().nextInt(10),
                new Random().nextInt(10, 100));
        publicationStatus = PublicationStatus.DRAFT;
    }
}
