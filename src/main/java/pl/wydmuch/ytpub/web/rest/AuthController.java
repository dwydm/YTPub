package pl.wydmuch.ytpub.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.wydmuch.ytpub.dto.UserAuthenticationDTO;
import pl.wydmuch.ytpub.service.UserService;
import pl.wydmuch.ytpub.service.auth.CustomUserDetailsService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final CustomUserDetailsService detailsService;

    @PostMapping("/login")
    public String getAuthenticated(Authentication authentication) {
        //detailsService.loadUserByUsername(authentication.getName());

        return authentication.getName();
    }

    @PostMapping("/register")
    public String createUser(@RequestBody UserAuthenticationDTO registeredDTO) {
        return userService.registerUser(registeredDTO);
    }
}
