package pl.wydmuch.ytpub.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDetailsDTO {
    private String id;
    private String email;
    private String password;
    private String name;
    private boolean userActive;
}
