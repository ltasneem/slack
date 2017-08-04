//package slack.controllers;
//
//import java.security.Principal;
//import java.sql.Date;
//import java.util.List;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import slack.models.Group;
//import slack.models.Message;
//import slack.models.User;
//import slack.services.GroupService;
//import slack.services.MessageService;
//import slack.services.UserService;
//
//@RestController
//public class MessagerController {
//	
//	@Autowired
//	private UserService userService;
//	@Autowired
//	private GroupService groupService;
//	@Autowired
//	private MessageService messageService;
//	
//	Group g = new Group();
//	
//	@RequestMapping("/addmessage")
//	public void addMessage(@RequestParam("message") String msg, Principal principal){
//		groupService.saveGroup(g);
//		User user = userService.findByUsername(principal.getName());
//		Message message = new Message();
//		message.setContent(msg);
//		message.setGroup(g);
//		message.setUser(user);
//		message.setTimestamp(new Date(System.currentTimeMillis()));
//		user.addMessage(message);
//		g.addMessage(message);
//		messageService.saveMessage(message);
//		userService.saveUser(user);
//		groupService.saveGroup(g);
//	}
//	
//	@RequestMapping("/getmessages")
//	public List<Message> getMessages(Principal principal) {
//		User user = userService.findByUsername(principal.getName());
//		return messageService.findByUser(user);
//	}
//
//}
