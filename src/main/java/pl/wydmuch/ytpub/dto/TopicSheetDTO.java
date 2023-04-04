package pl.wydmuch.ytpub.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
@Data
@Builder
public class TopicSheetDTO {
    private String id;
    private String title;
    private String description;
    private String topicSerialNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDate creationDate;
    private String coauthorStatus;
    private String publicationStatus;
    private UserSimplifiedDTO mainAuthor;
    private Set<UserSimplifiedDTO> coauthors;
    private Set<SourceDTO> sources;
}
