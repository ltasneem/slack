package slack.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
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
	
    @RequestMapping(value = "/editprofile", method = RequestMethod.GET)
	public String editProfile(Principal principal, Model model) {
    	User user = userService.findByUsername(principal.getName());
    	model.addAttribute("user", user);
    	System.out.println(user.getPassword());
        return "editprofile";
	}
    
    @RequestMapping(value = "/editProfile", method = RequestMethod.POST)
	public String processeditProfile(Principal principal,@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {	
    	model.addAttribute("user", user);
    	User edituser=userService.findByUsername(principal.getName());
    	System.out.println(edituser.getId());
    	System.out.println(user.getId());
    	System.out.println(user.getFirstName());
    	System.out.println(user.getEmail());
    	System.out.println(user.getLastName());
    	System.out.println(user.getUsername());
    	System.out.println(edituser.getPassword());
 
    	System.out.println(edituser.getPassword());
        if ( user.getFirstName().equals("") ||user.getEmail().equals("") || user.getLastName().equals("") || user.getUsername().equals("") ) {
            return "editprofile";
        } else {
        	user.setId(edituser.getId());
        	//user.setLastName(edituser.getLastName());
        	//user.setUsername(edituser.getUsername());
        	//user.setEmail(edituser.getEmail());
        	user.setPassword(edituser.getPassword());
            userService.saveUser(user);
            model.addAttribute("message", "User Account Successfully Created");
        }
        return "userprofile";
	}
    
    @RequestMapping(value = "/passwordchange", method = RequestMethod.GET)
   	public String processpasswordchange(Principal principal, Model model) {
       	User user = userService.findByUsername(principal.getName());
       	model.addAttribute("user", user);
       	System.out.println(user.getPassword());
           return "passwordchange";
   	}
    
    @RequestMapping(value = "/passwordchange",method = RequestMethod.POST)
	public String processedpassword(Principal principal,@Valid @ModelAttribute("user") User user, BindingResult result, Model model,@RequestParam("old_pass") String old_pass,@RequestParam("new_pass") String new_pass,@RequestParam("confirm_new_pass") String confirm_new_pass) {	
    	model.addAttribute("user", user);
    	User edituser=userService.findByUsername(principal.getName());
    	System.out.println(edituser.getPassword());
    	System.out.println(userService.checkpassword(old_pass, edituser.getPassword()));

    	System.out.println(edituser.getPassword());
        if ( !new_pass.equals(confirm_new_pass)  || new_pass.equals("") || old_pass.equals("") || !userService.checkpassword(old_pass, edituser.getPassword())) {
        	System.out.println("hello");
            return "passwordchange";
        } else {
        	user.setId(edituser.getId());
        	user.setFirstName(edituser.getFirstName());
        	user.setLastName(edituser.getLastName());
        	user.setUsername(edituser.getUsername());
        	user.setPassword(new_pass);
        	user.setEmail(edituser.getEmail());
            userService.saveUser(user);
            model.addAttribute("message", "User Account Successfully Created");
        }
        return "userprofile";
	}
    
    /*@RequestMapping("/userprofile")
    public String userprofile(Principal principal,Model model){
    	User user = userService.findByUsername(principal.getName());
    	//System.out.println(user.getUsername());
    	model.addAttribute("user", user);
        return "userprofile";
    }*/
}