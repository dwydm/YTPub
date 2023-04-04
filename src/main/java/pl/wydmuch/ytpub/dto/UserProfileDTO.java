package pl.wydmuch.ytpub.dto;


import lombok.Builder;
import lombok.Data;

import java.util.Set;


@Data
@Builder
public class UserProfileDTO {
    private String id;
    private String email;
    private String name;
    private String role;

    private Set<TopicDetailsDTO> mainTopics;
    private Set<TopicDetailsDTO> secondaryTopics;

}
