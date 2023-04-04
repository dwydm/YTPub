package pl.wydmuch.ytpub.mapper;

import pl.wydmuch.ytpub.dto.SourceDTO;
import pl.wydmuch.ytpub.model.Source;

public class SourceMapper {

    public static SourceDTO toSourceDTO(Source source) {
        return SourceDTO.builder()
                .id(source.getId().toString())
                .topic(TopicMapper.toDetailsDTO(source.getTopic()))
                .description(source.getDescription())
                .sourceType(source.getSourceType().name())
                .sourceLink(source.getSourceLink())
                .sourceDate(source.getSourceDate())
                .sourceAuthor(UserMapper.toSimplifiedDTO(source.getSourceAuthor()))
                .build();
    }
}
