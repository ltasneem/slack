package slack.repositories;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import slack.models.User;

public interface UserRepository extends CrudRepository<User, Long>{
    User findByUsername(String username);
    User findByEmail(String email);
    Long countByEmail(String email);
    Long countByUsername(String username);
    Iterable<User> findAll();
}