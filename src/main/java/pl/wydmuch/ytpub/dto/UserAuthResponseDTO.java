package pl.wydmuch.ytpub.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserAuthResponseDTO {
    private String id;
    private String email;
    private String name;
    private String token;
    private String role;
}
