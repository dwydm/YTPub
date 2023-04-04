package pl.wydmuch.ytpub.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "sources")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.PRIVATE)
    private UUID id;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SourceType sourceType;
    private String sourceLink;
    @DateTimeFormat(pattern = "dd/MM/yy")
    private LocalDate sourceDate;
    @ManyToOne
    private User sourceAuthor;
    @ManyToOne
    private Topic topic;

    @PrePersist
    private void generateSourceDate() {
        this.sourceDate = LocalDate.now();
    }
}
