package slack.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import slack.models.Group;
import slack.models.Message;
import slack.models.User;

public interface MessageRepository extends CrudRepository<Message,Long>{
	
	List<Message> findByUser(User u);
	List<Message> findByGroup(Group g);
	
}
