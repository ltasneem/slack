package slack.controllers;

import java.io.IOException;
import java.security.Principal;
import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import slack.models.Group;
import slack.models.Message;
import slack.models.User;
import slack.services.GroupService;
import slack.services.MessageService;
import slack.services.UserService;

@RestController
public class MessageController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private MessageService messageService;
	
	@RequestMapping("/addmessage")
	public void addMessage(@RequestParam("message") String msg, @Valid @ModelAttribute("group") Group group, Principal principal){
		User user = userService.findByUsername(principal.getName());
		Message message = new Message();
		message.setContent(msg);
		message.setGroup(group);
		message.setUser(user);
		message.setTimestamp(new Date(System.currentTimeMillis()));
		user.addMessage(message);
		group.addMessage(message);
		messageService.saveMessage(message);
		userService.saveUser(user);
		groupService.saveGroup(group);
	}
	
	@RequestMapping("/getmessages")
	public String getMessages(Principal principal, @Valid @ModelAttribute("group") Group group) {
		User user = userService.findByUsername(principal.getName());
		List<Message> messages = messageService.findByUser(user);
		StringBuilder sb = new StringBuilder();
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonInString = mapper.writeValueAsString(messages.get(0));
			System.out.println(jsonInString);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

}
