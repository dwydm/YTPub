package pl.wydmuch.ytpub.mapper;

import pl.wydmuch.ytpub.dto.TopicDetailsDTO;
import pl.wydmuch.ytpub.dto.TopicSheetDTO;
import pl.wydmuch.ytpub.model.Topic;

import java.util.stream.Collectors;

public class TopicMapper {


    public static TopicSheetDTO toSheetDTO(Topic topic) {
        return TopicSheetDTO.builder()
                .id(topic.getId().toString())
                .title(topic.getTitle())
                .description(topic.getDescription())
                .mainAuthor(UserMapper.toSimplifiedDTO(topic.getMainAuthor()))
                .coauthorStatus(topic.getCoauthorStatus().name())
                .topicSerialNumber(topic.getTopicSerialNumber())
                .publicationStatus(topic.getPublicationStatus().name())
                .creationDate(topic.getCreationDate())
                .coauthors(topic.getCoauthors().stream()
                        .map(UserMapper::toSimplifiedDTO)
                        .collect(Collectors.toSet()))
                .sources(topic.getSources().stream()
                        .map(SourceMapper::toSourceDTO)
                        .collect(Collectors.toSet()))
                .build();
    }

    public static TopicDetailsDTO toDetailsDTO(Topic topic) {
        return TopicDetailsDTO.builder()
                .id(topic.getId().toString())
                .title(topic.getTitle())
                .description(topic.getDescription())
                .mainAuthor(UserMapper.toSimplifiedDTO(topic.getMainAuthor()))
                .coauthorStatus(topic.getCoauthorStatus().name())
                .topicSerialNumber(topic.getTopicSerialNumber())
                .publicationStatus(topic.getPublicationStatus().name())
                .creationDate(topic.getCreationDate())
                .build();
    }

}
