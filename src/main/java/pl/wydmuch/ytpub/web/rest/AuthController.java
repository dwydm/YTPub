package pl.wydmuch.ytpub.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.wydmuch.ytpub.dto.UserAuthResponseDTO;
import pl.wydmuch.ytpub.dto.UserAuthenticationDTO;
import pl.wydmuch.ytpub.service.UserService;

import java.util.Base64;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/login")
    public UserAuthResponseDTO getAuthenticated(@RequestBody UserAuthenticationDTO authenticationDTO) {
        if(userService.isUserValid(authenticationDTO)) {
            return userService.authenticate(authenticationDTO);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/register")
    public String createUser(@RequestBody UserAuthenticationDTO registeredDTO) {
        return userService.registerUser(registeredDTO);
    }
}
