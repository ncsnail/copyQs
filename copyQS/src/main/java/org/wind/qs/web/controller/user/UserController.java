package org.wind.qs.web.controller.user;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.wind.qs.entity.User;
import org.wind.qs.service.ServiceException;
import org.wind.qs.service.user.UserService;


@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String getList(Model model){
		try{
			List<User> ulist =  userService.getUserList();
			if(ulist != null && ulist.size()>0){
				model.addAttribute("users",ulist);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "user/list";
	}
	
	@RequestMapping(value="update/{id}")
	public String get(@PathVariable("id") long id,Model model){
		try{
			User user = userService.getUser(id);
			model.addAttribute("user", user);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "user/updateForm";
	}
	
	@RequestMapping(value="update", method= RequestMethod.POST)
	public String update(@Valid User user,RedirectAttributes rdAttr){
		try{
			User user_t = userService.getUser(user.getId());
			
			if(StringUtils.isNotBlank(user.getName())){
				user_t.setName(user.getName());
			}
			if(StringUtils.isNotBlank(user.getPassword())){
				user_t.setPassword(user.getPassword());
			}
			userService.updateUser(user_t);
		}catch(Exception e){
			e.printStackTrace();
		}
		rdAttr.addFlashAttribute("msg", "修改成功");
		return "redirect:/user";
	}
	
	@RequestMapping(value="delete/{id}")
	public String delete(@PathVariable("id") long id, RedirectAttributes rdAttr ){	
		try{
			userService.deleteUser(id);
			rdAttr.addFlashAttribute("sucMsg", "删除成功");
		}catch(ServiceException s){
			s.printStackTrace();
			rdAttr.addFlashAttribute("errMsg", s.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			rdAttr.addFlashAttribute("errMsg", "删除失败");
		}
		
		return "redirect:/user";
	}
	
	
}
