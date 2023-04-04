package pl.wydmuch.ytpub.web.rest;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wydmuch.ytpub.dto.TopicDetailsDTO;
import pl.wydmuch.ytpub.dto.TopicSheetDTO;
import pl.wydmuch.ytpub.service.PublicationService;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@Slf4j
@RequestMapping("/api/topics")
public class PublicationController {
    private final PublicationService publicationService;


    @GetMapping("/list")
    public List<TopicDetailsDTO> getTopicDetailsList() {
        return publicationService.getTopicDetailsList();
    }

    @GetMapping("/{id}")
    public TopicSheetDTO getFullTopicSheet(@PathVariable String id) {
        return publicationService.getTopicSheet(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public String createNewTopic(@RequestBody TopicDetailsDTO detailsDTO) {
        return publicationService.createTopic(detailsDTO).getId();
    }

    @PostMapping("/{id}/add")
    public void addNewSource(@PathVariable("id") String id) {

    }
}
