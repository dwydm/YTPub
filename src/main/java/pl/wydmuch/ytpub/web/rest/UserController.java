package pl.wydmuch.ytpub.web.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.wydmuch.ytpub.dto.UserDetailsDTO;
import pl.wydmuch.ytpub.dto.UserProfileDTO;
import pl.wydmuch.ytpub.service.UserService;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/list")
    public List<UserDetailsDTO> getUsers() {
        log.info(SecurityContextHolder.getContext().getAuthentication().getName());
        return userService.getUserDetailsList();
    }

    @GetMapping("/profile/{id}")
    public UserProfileDTO getUserProfile(@PathVariable("id") String id) {
        return userService.getUserProfileById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/update/{id}")
    public void updateDetails(@PathVariable String id, @RequestBody UserDetailsDTO detailsDTO) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        if(currentUser.isAuthenticated() && (userService.findSimplifiedDTOByEmail(currentUser.getName()).getId().equals(id) ||
                currentUser.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ADMIN")))) {
            userService.updateUserDetails(id, detailsDTO);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Unauthorized action");
        }
    }
    @GetMapping("/update/active/{id}")
    public void userAccountActivation(@PathVariable("id") String id) {
        userService.updateUserActive(id);
    }
}
