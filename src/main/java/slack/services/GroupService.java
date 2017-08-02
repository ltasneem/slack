package slack.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import slack.models.Group;
import slack.models.Message;
import slack.models.User;
import slack.repositories.GroupRepository;
import slack.repositories.UserRepository;

@Service
public class GroupService {
	
	@Autowired
	GroupRepository groupRepository;
	
	@Autowired
	UserRepository userRepository;
	
	Group findByName(String name) {
		return groupRepository.findByName(name);
	}
//	Group findByUser(User user) {
//		return groupRepository.findByUser(user);
//	}

	//may need one for saving users
   public void saveGroup(Group group) {
	   
	   	//set message
	   
	   
	   
//        user.setRoles(Arrays.asList(roleRepository.findByRole("USER")));
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setEnabled(true);
        groupRepository.save(group);
    }

}
