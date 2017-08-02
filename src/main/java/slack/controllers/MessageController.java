package slack.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
	
	@RequestMapping("/getmessages")
	public String getMessages(@RequestParam("message") String msg){
		return "big";
	}

}
