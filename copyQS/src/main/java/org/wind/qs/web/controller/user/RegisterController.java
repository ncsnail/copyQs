package org.wind.qs.web.controller.user;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.wind.qs.entity.User;
import org.wind.qs.service.user.UserService;


@Controller
@RequestMapping(value = "/register")
public class RegisterController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String toRegister(){
		return "user/register";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String register(@Valid User user,RedirectAttributes rAttr){
		
		try{
			userService.registerUser(user);
			rAttr.addAttribute("username", user.getLoginName());
			UsernamePasswordToken token = new UsernamePasswordToken(user.getLoginName(),user.getPassword());
			Subject subject = SecurityUtils.getSubject();
			if(!subject.isAuthenticated()){
				subject.login(token);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "index";
	}
	
	@RequestMapping(value="checkLoginName")
	@ResponseBody
	public String checkLoginName(@RequestParam("loginName") String loginName){
		String result = "true";
		try{
			if(userService.findByLoginName(loginName)!= null){
				result = "false";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
}
