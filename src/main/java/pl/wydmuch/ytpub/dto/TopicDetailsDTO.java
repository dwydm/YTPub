package pl.wydmuch.ytpub.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class TopicDetailsDTO {
    private String id;
    private String title;
    private String description;
    private String topicSerialNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDate creationDate;
    private UserSimplifiedDTO mainAuthor;
    private String coauthorStatus;
    private String publicationStatus;
}
