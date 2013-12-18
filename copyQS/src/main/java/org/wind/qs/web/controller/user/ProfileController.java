package org.wind.qs.web.controller.user;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.wind.qs.entity.User;
import org.wind.qs.service.user.UserService;
import org.wind.qs.to.ShiroUser;

@Controller
@RequestMapping(value ="/profile")
public class ProfileController {
	
	
	@Autowired 
	UserService userService;
	
	//
	@RequestMapping(method = RequestMethod.GET)
	public String toProfile(Model model){
		
		try{
			ShiroUser suser = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
			User user = userService.getUser(suser.getId());
			model.addAttribute("user", user);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "user/profile";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String updateProfile(@Valid @ModelAttribute User user,Model model){
		try{
			User us1 = userService.getUser(user.getId());
			us1.setName(user.getName());
			if(StringUtils.isNotEmpty(user.getPassword())){
				us1.setPassword(user.getPassword());
			}
			userService.updateUser(us1);
			model.addAttribute("msg", "更新用户成功!");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return "user/profile";
	}
	
}
