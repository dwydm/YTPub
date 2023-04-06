package pl.wydmuch.ytpub.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pl.wydmuch.ytpub.model.topic.CoauthorStatus;
import pl.wydmuch.ytpub.model.topic.PublicationStatus;
import pl.wydmuch.ytpub.model.topic.Topic;
import pl.wydmuch.ytpub.model.user.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface TopicRepository extends CrudRepository<Topic, UUID> {
    Optional<Topic> findByTopicSerialNumber(String serialNumber);

    List<Topic> findAllByMainAuthor(User user);

    List<Topic> findAllByCoauthorStatus(CoauthorStatus status);

    List<Topic> findAllByPublicationStatus(PublicationStatus status);

    List<Topic> findAllByTitleContainingIgnoreCase(String title);

    @Query("SELECT t FROM Topic t JOIN t.coauthors authors WHERE authors = :author")
    List<Topic> findByCoAuthor(User author);

    @Query("SELECT DISTINCT t FROM Topic t JOIN t.coauthors coautors WHERE coautors IN :authors")
    List<Topic> findByAnyCoAuthor(@Param("authors") Set<User> authors);
}
