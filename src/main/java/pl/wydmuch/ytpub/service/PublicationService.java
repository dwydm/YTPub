package pl.wydmuch.ytpub.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wydmuch.ytpub.dto.TopicDetailsDTO;
import pl.wydmuch.ytpub.dto.TopicSheetDTO;
import pl.wydmuch.ytpub.mapper.TopicMapper;
import pl.wydmuch.ytpub.model.topic.CoauthorStatus;
import pl.wydmuch.ytpub.model.topic.PublicationStatus;
import pl.wydmuch.ytpub.model.topic.Topic;
import pl.wydmuch.ytpub.repository.SourceRepository;
import pl.wydmuch.ytpub.repository.TopicRepository;
import pl.wydmuch.ytpub.repository.UserRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class PublicationService {
    private final SourceRepository sourceRepository;
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;


    public List<TopicDetailsDTO> getTopicDetailsList() {
        return StreamSupport.stream(topicRepository.findAll().spliterator(),false).map(TopicMapper::toDetailsDTO).collect(Collectors.toList());
    }

    public TopicSheetDTO getTopicSheet(String id) {
        return TopicMapper.toSheetDTO(topicRepository.findById(UUID.fromString(id)).orElseThrow(() -> new RuntimeException("Unable to find topic id " + id)));
    }

    public TopicDetailsDTO createTopic(TopicDetailsDTO detailsDTO) {
        Topic createdTopic = Topic.builder()
                .title(detailsDTO.getTitle())
                .description(detailsDTO.getDescription())
                .mainAuthor(userRepository.findById(UUID.fromString(detailsDTO.getMainAuthor().getId())).orElseThrow(() -> new RuntimeException("Unable to find user")))
                .coauthorStatus(CoauthorStatus.valueOf(detailsDTO.getCoauthorStatus()))
                .publicationStatus(PublicationStatus.DRAFT)
                .build();
        createdTopic = topicRepository.save(createdTopic);
        return TopicMapper.toDetailsDTO(createdTopic);
    }
}
