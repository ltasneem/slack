package slack.controllers;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import slack.validators.UserValidator;


@Controller
public class HomeController {
	
	@Autowired
	private UserValidator userValidator;
	
	@RequestMapping("/")
	   public String index(Model model){
	        return "homepage";
	  }
	
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

}
