package org.wind.qs.web.controller.task;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value="/task")
public class TaskController {
	
	@RequestMapping(method = RequestMethod.GET)
	public String getList(){
		
		
		
		return "task/list";
	}
}
