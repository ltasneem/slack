package slack.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import slack.models.User;
import slack.services.UserService;
@Controller

public class EditProfileController {
	@Autowired
	private UserService userService;
	@Autowired
    private PasswordEncoder passwordEncoder;
	
    @RequestMapping(value = "/editprofile", method = RequestMethod.GET)
	public String editProfile(Principal principal, Model model) {
    	User user = userService.findByUsername(principal.getName());
    	//System.out.println(user.getUsername());
    	model.addAttribute("user", user);
        return "editprofile";
	}
    
    @RequestMapping(value = "/editProfile", method = RequestMethod.POST)
	public String processeditProfile(Principal principal,@Valid @ModelAttribute("user") User user, BindingResult result, Model model,@RequestParam("new_pass") String new_pass,@RequestParam("confirm_new_pass") String confirm_new_pass) {	
    	model.addAttribute("user", user);
    	User edituser=userService.findByUsername(principal.getName());
    	System.out.println(edituser.getId());
    	System.out.println(user.getId());
    	System.out.println(user.getFirstName());
    	System.out.println(user.getEmail());
    	System.out.println(user.getLastName());
    	System.out.println(user.getUsername());
    	System.out.println(user.getPassword());
    	//System.out.println(passwordEncoder.encode(old_pass));
    	//System.out.println(passwordEncoder.encode(old_pass));
    	System.out.println(edituser.getPassword());
        if (user.getFirstName().equals("") || !new_pass.equals(confirm_new_pass) || 
        		user.getEmail().equals("") || user.getLastName().equals("") || user.getUsername().equals("") 
        		 || new_pass.equals("") ) {
        	System.out.println("hello");
            return "editprofile";
        } else {
        	System.out.println("lubaba");
        	user.setId(edituser.getId());
        	user.setPassword(new_pass);
            userService.saveUser(user);
            model.addAttribute("message", "User Account Successfully Created");
        }
        return "userprofile";
	}
    
    @RequestMapping("/userprofile")
    public String userprofile(Principal principal,Model model){
    	User user = userService.findByUsername(principal.getName());
    	//System.out.println(user.getUsername());
    	model.addAttribute("user", user);
        return "userprofile";
    }
}
