package pl.wydmuch.ytpub.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.wydmuch.ytpub.dto.UserAuthenticationDTO;
import pl.wydmuch.ytpub.dto.UserDetailsDTO;
import pl.wydmuch.ytpub.dto.UserProfileDTO;
import pl.wydmuch.ytpub.dto.UserSimplifiedDTO;
import pl.wydmuch.ytpub.mapper.UserMapper;
import pl.wydmuch.ytpub.model.Role;
import pl.wydmuch.ytpub.model.User;
import pl.wydmuch.ytpub.repository.UserRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public User save(User user) {
        return userRepository.save(user);
    }

    public UserProfileDTO getUserProfileById(String id) {
        return UserMapper.toProfileDTO(userRepository.findById(UUID.fromString(id)).orElseThrow(() -> new RuntimeException("User id " + id + " not found")));
    }

    public List<UserDetailsDTO> getUserDetailsList() {
        return StreamSupport.stream(userRepository.findAllUsers().spliterator(),false).map(UserMapper::toDetailsDTO).collect(Collectors.toList());
    }

    public User updateUserDetails(String id, UserDetailsDTO detailsDTO) {
        if(detailsDTO == null) {
            throw new RuntimeException("No data provided");
        }
        User existingUser = userRepository.findById(UUID.fromString(id)).orElseThrow(() -> new RuntimeException("User id " + id + " not found"));
        if(detailsDTO.getPassword() != null) {
            String passwordDTO = encoder.encode(detailsDTO.getPassword());
            if(!passwordDTO.equals(existingUser.getPassword())) {
                existingUser.setPassword(passwordDTO);
            }
        }
        if(detailsDTO.getEmail() != null) {
            existingUser.setEmail(detailsDTO.getEmail());
        }
        if(detailsDTO.getName() != null) {
            existingUser.setName(detailsDTO.getName());
        }
        return userRepository.save(existingUser);
    }

    public User updateUserActive(String id) {
        User existingUser = userRepository.findById(UUID.fromString(id)).orElseThrow(() -> new RuntimeException("User id " + id + " not found"));
        if(existingUser.isUserActive()) {
            existingUser.setUserActive(false);
        } else {
            existingUser.setUserActive(true);
        }
        return userRepository.save(existingUser);
    }

    public String registerUser(UserAuthenticationDTO authenticationDTO) {
        if(!userRepository.existsByEmailIgnoreCase(authenticationDTO.getEmail())) {
            userRepository.save(User.builder()
                            .email(authenticationDTO.getEmail().toLowerCase())
                            .password(encoder.encode(authenticationDTO.getPassword()))
                            .name(authenticationDTO.getEmail().replaceAll("[.].{1,}|@.{1,}",""))
                            .role(Role.EDITOR)
                    .build());

        } else {
            return "E-mail " + authenticationDTO.getEmail() + " already exists in database";
        }
        return "success";
    }

    public UserSimplifiedDTO findSimplifiedDTOByEmail(String email) {
        return UserMapper.toSimplifiedDTO(userRepository.findByEmailIgnoreCase(email).orElseThrow(() -> new RuntimeException("Unable to find user")));
    }

}
