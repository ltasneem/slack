package slack.repositories;

import org.springframework.data.repository.CrudRepository;

import slack.models.Group;
import slack.models.User;

public interface GroupRepository extends CrudRepository<Group, Long> {
	
	Group findByName(String name);
	Group findByUser(User user);

}
