package pl.wydmuch.ytpub.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserSimplifiedDTO {
    private String id;
    private String name;
}
