package pl.wydmuch.ytpub.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.wydmuch.ytpub.model.*;

import java.util.List;
import java.util.UUID;

public interface SourceRepository extends CrudRepository<Source, UUID> {
    List<Source> findAllBySourceAuthor(User user);

    List<Source> findAllByTopic(Topic topic);

    List<Source> findAllBySourceType(SourceType type);

    @Query("SELECT s FROM Source s JOIN s.topic topic WHERE topic.topicSerialNumber = :serialNumber")
    List<Source> findSourcesByTopicSerialNumber(String serialNumber);

    @Query("SELECT s FROM Source s JOIN s.topic topic WHERE topic.publicationStatus = :status")
    List<Source> findSourcesByTopicStatus(PublicationStatus status);

}
