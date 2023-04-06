package pl.wydmuch.ytpub.mapper;

import pl.wydmuch.ytpub.dto.*;
import pl.wydmuch.ytpub.model.User;

import java.util.stream.Collectors;


public class UserMapper {

    public static UserProfileDTO toProfileDTO(User user) {
        return UserProfileDTO.builder()
                .id(user.getId().toString())
                .email(user.getEmail())
                .name(user.getName())
                .role(user.getRole().name())
                .mainTopics(user.getMainTopics().stream()
                        .map(TopicMapper::toDetailsDTO)
                        .collect(Collectors.toSet()))
                .secondaryTopics(user.getSecondaryTopics().stream()
                        .map(TopicMapper::toDetailsDTO)
                        .collect(Collectors.toSet()))
                .build();
    }

    public static UserDetailsDTO toDetailsDTO(User user) {
        return UserDetailsDTO.builder()
                .id(user.getId().toString())
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .userActive(user.isUserActive())
                .build();
    }

    public static UserSimplifiedDTO toSimplifiedDTO(User user) {
        return UserSimplifiedDTO.builder()
                .id(user.getId().toString())
                .name(user.getName())
                .build();
    }

    public static UserAuthenticationDTO toAuthenticationDTO(User user) {
        return UserAuthenticationDTO.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public  static UserAuthResponseDTO toAuthResponseDTO(User user, String token) {
        return UserAuthResponseDTO.builder()
                .id(user.getId().toString())
                .email(user.getEmail())
                .name(user.getName())
                .token(token)
                .role(user.getRole().name())
                .build();
    }
}
