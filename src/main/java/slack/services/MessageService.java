package slack.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import slack.models.Message;
import slack.models.User;
import slack.repositories.MessageRepository;

public class MessageService {
	
	@Autowired
	private MessageRepository messRepo;
	
	public void saveMessage(Message m) {
		messRepo.save(m);
	}
	
	public List<Message> findByUser(User u) {
		return messRepo.findByUser(u);
	}
	
	public List<Message> findByGroup(Group g) {
		return messRepo.findByGroup(g);
	}
	
//	public void delete(Message m) {
//		messRepo.delete(m);
//	}
	

}
