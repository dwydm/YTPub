package pl.wydmuch.ytpub.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class SourceDTO {
    private String id;
    private String description;
    private String sourceType;
    private String sourceLink;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yy")
    private LocalDate sourceDate;
    private UserSimplifiedDTO sourceAuthor;
    private TopicDetailsDTO topic;

}
