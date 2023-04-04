package pl.wydmuch.ytpub.helper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import pl.wydmuch.ytpub.dto.TopicDetailsDTO;
import pl.wydmuch.ytpub.dto.UserAuthenticationDTO;
import pl.wydmuch.ytpub.model.Role;
import pl.wydmuch.ytpub.model.User;
import pl.wydmuch.ytpub.service.PublicationService;
import pl.wydmuch.ytpub.service.UserService;

import java.util.HashSet;

@Component
@Slf4j
@RequiredArgsConstructor
public class StarterDataInitializer implements CommandLineRunner {
    private final UserService userService;
    private final PublicationService pubService;
    private final BCryptPasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        //Admin
        userService.save(User.builder()
                .email("admin@ytp.app")
                .password(encoder.encode("admin"))
                .role(Role.ADMIN)
                .name("Admin")
                .userActive(true)
                .build());

        //Users
        UserAuthenticationDTO[] users = new UserAuthenticationDTO[]{
                UserAuthenticationDTO.builder()
                        .email("jason.domofon@call.me")
                        .password("qwerty")
                        .build(),
                UserAuthenticationDTO.builder()
                        .email("michael.klakson@king.pop")
                        .password("asdf").build(),
                UserAuthenticationDTO.builder().email("bob.dywan@fly.it")
                        .password("zxcvb")
                        .build(),
                UserAuthenticationDTO.builder()
                        .email("mick-zagiel@jastarnia.pl")
                        .password("passcom").build(),
                UserAuthenticationDTO.builder()
                        .email("Tani.Bojler@goodbye.say")
                        .password("hello").build(),
                UserAuthenticationDTO.builder()
                        .email("paul.mckarton@gmail.com")
                        .password("ytrewq")
                        .build()
        };

        for (UserAuthenticationDTO userDTO : users) {
            userService.registerUser(userDTO);
        }

        //Topics and Sources
        TopicDetailsDTO[] topics = new TopicDetailsDTO[]{
                TopicDetailsDTO.builder()
                        .title("Solo, PUBLISHED")
                        .description("Sources: 0")
                        .coauthorStatus("SOLO")
                        .mainAuthor(userService.findSimplifiedDTOByEmail("jason.domofon@call.me"))
                        .publicationStatus("PUBLISHED")
                        .build(),
                TopicDetailsDTO.builder()
                        .title("OPEN, DRAFT - One Co-Author")
                        .description("Sources: 0")
                        .coauthorStatus("OPEN")
                        .mainAuthor(userService.findSimplifiedDTOByEmail("michael.klakson@king.pop"))
                        .publicationStatus("DRAFT")
                        .build(),
        };

        for (TopicDetailsDTO detailsDTO : topics) {
            pubService.createTopic(detailsDTO);
        }


    }
}
