package pl.wydmuch.ytpub.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.wydmuch.ytpub.dto.*;
import pl.wydmuch.ytpub.mapper.UserMapper;
import pl.wydmuch.ytpub.model.Role;
import pl.wydmuch.ytpub.model.User;
import pl.wydmuch.ytpub.repository.UserRepository;
import pl.wydmuch.ytpub.service.auth.CustomUserDetailsService;

import java.util.Base64;
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

    public boolean existsByLogin(String email){
        return userRepository.existsByEmailIgnoreCase(email);
    }

    public boolean existsById(String id) {
        return userRepository.existsById(UUID.fromString(id));
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
        final String NAME_FROM_EMAIL_REGEX = "[.].{1,}|@.{1,}";

        if(!userRepository.existsByEmailIgnoreCase(authenticationDTO.getEmail())) {
            userRepository.save(User.builder()
                            .email(authenticationDTO.getEmail().toLowerCase())
                            .password(encoder.encode(authenticationDTO.getPassword()))
                            .name(authenticationDTO.getEmail().replaceAll(NAME_FROM_EMAIL_REGEX,""))
                            .role(Role.EDITOR)
                    .build());

        } else {
            return "E-mail " + authenticationDTO.getEmail() + " already exists in database";
        }
        return "success";
    }

    public boolean isUserValid(UserAuthenticationDTO authenticationDTO) {
        if(encoder.matches(authenticationDTO.getPassword(), userRepository.findByEmailIgnoreCase(authenticationDTO.getEmail()).orElseThrow(() -> new RuntimeException("Invalid username")).getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    public UserAuthResponseDTO authenticate(UserAuthenticationDTO authenticationDTO){
        User existingUser = userRepository.findByEmailIgnoreCase(authenticationDTO.getEmail()).orElseThrow(() -> new RuntimeException("Invalid username"));
        Authentication auth = new UsernamePasswordAuthenticationToken(authenticationDTO.getEmail(),authenticationDTO.getPassword());
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        String token = Base64.getEncoder().encodeToString((authenticationDTO.getEmail() + ":" + authenticationDTO.getPassword()).getBytes());
        return UserMapper.toAuthResponseDTO(existingUser,token);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserDetailsDTO findUserDTOByEmail(String email){
        return UserMapper.toDetailsDTO(userRepository.findByEmailIgnoreCase(email).orElseThrow(() -> new RuntimeException("Unable to find user")));
    }

    public UserSimplifiedDTO findSimplifiedDTOByEmail(String email) {
        return UserMapper.toSimplifiedDTO(userRepository.findByEmailIgnoreCase(email).orElseThrow(() -> new RuntimeException("Unable to find user")));
    }

}
