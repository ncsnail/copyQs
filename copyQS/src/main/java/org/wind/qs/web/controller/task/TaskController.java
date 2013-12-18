package org.wind.qs.web.controller.task;

import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.wind.qs.entity.Task;
import org.wind.qs.entity.User;
import org.wind.qs.service.task.TaskService;
import org.wind.qs.to.ShiroUser;

import com.google.common.collect.Maps;


@Controller
@RequestMapping(value="/task")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	private static final String PAGE_SIZE = "4";
	
	private static Map<String,String> sortTypes = Maps.newLinkedHashMap();
	static{
		sortTypes.put("atuo", "自动");
		sortTypes.put("title", "标题");
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String getList(@RequestParam(value="page",defaultValue="1") int pageNum,
			@RequestParam(value="page.size",defaultValue = PAGE_SIZE) int pageSize,
			@RequestParam(value="sortType",defaultValue="atuo")String sortType, Model model,ServletRequest request){
		
		try{
			String searchParams = request.getParameter("search_LIKE_title");
			System.out.println("searchParams = "+searchParams);
			Page<Task> tasks = taskService.getUserTask(getCurrentUserId(),searchParams,pageNum,pageSize,sortType);
			
			if(tasks != null){
				model.addAttribute("tasks", tasks);
			}
			model.addAttribute("sortType", sortType);
			model.addAttribute("sortTypes", sortTypes);
			//to avoid searchParams become 'null'
			if(StringUtils.isNotBlank(searchParams)){
				model.addAttribute("searchParams","search_LIKE_title="+searchParams);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}

		return "task/list";
	}
	
	public Long getCurrentUserId(){
		ShiroUser su = (ShiroUser) 	SecurityUtils.getSubject().getPrincipal();	
		return su.getId();
	}
	
	@RequestMapping(value="create",method = RequestMethod.GET)
	public String toCreateForm(Model model){
		model.addAttribute("action", "create");
		return"task/form";
	}
	
	@RequestMapping(value="create",method = RequestMethod.POST)
	public String create(Task task,RedirectAttributes redirectAttributes){
		
		try{
			if(task != null){
				
				User curUser = new User();
				curUser.setId(getCurrentUserId());
				task.setUser(curUser);
				taskService.saveTask(task);
				redirectAttributes.addFlashAttribute("message", "任务创建成功");
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return "redirect:/task/";
	}
	
	
	@RequestMapping(value="update/{id}",method=RequestMethod.GET)
	public String toUpdateForm(@PathVariable("id") Long id, Model model){
		
		try{
			Task task = taskService.getTask(id); 
			model.addAttribute("task", task);
			model.addAttribute("action", "update");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return"task/form";
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	public String update(Task task,RedirectAttributes redirectAttributes){
		try{
			if(task != null){
				Task temp = taskService.getTask(task.getId());
				temp.setTitle(task.getTitle());
				temp.setDescription(task.getDescription());
				taskService.saveTask(temp);
				redirectAttributes.addFlashAttribute("message", "任务修改成功");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "redirect:/task";
	}
	
	@RequestMapping(value="delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable("id") Long id,RedirectAttributes redirectAttributes){
		
		try{
			taskService.deleteTask(id);
			redirectAttributes.addFlashAttribute("message", "用户删除成功");
		}catch(Exception e){
			e.printStackTrace();
		}
		return "redirect:/task"; 
	}
	
	
}
