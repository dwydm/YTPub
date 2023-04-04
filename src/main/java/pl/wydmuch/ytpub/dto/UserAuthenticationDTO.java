package pl.wydmuch.ytpub.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAuthenticationDTO {
    private String email;
    private String password;
}
