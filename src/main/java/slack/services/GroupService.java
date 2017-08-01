package slack.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import slack.models.Group;
import slack.models.User;
import slack.repositories.GroupRepository;

@Service
public class GroupService {
	
	@Autowired
	GroupRepository groupRepository;
	
	Group findByName(String name) {
		return groupRepository.findByName(name);
	}
	Group findByUser(User user) {
		return groupRepository.findByUser(user);
	}

	//may need one for saving users
   public void saveMessage(Message msg) {
	   
	   	//set message
	   
	   
	   
//        user.setRoles(Arrays.asList(roleRepository.findByRole("USER")));
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setEnabled(true);
        msgRepository.save(msg);
    }

}
