package com.founder.pod.galihezi.www.web.action.demo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.founder.pod.galihezi.basis.domain.demo.DemoDO;
import com.founder.pod.galihezi.basis.service.demo.IDemoService;

@Controller
public class DemoAction {
	
	@Autowired
	private IDemoService demoService;
	
	@RequestMapping(value="/welcome.htm",method={RequestMethod.GET})
	public ModelAndView welcome(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("welcome");
		int sum = 0;
		List<DemoDO> list = demoService.findDemoDO(new DemoDO());
		if(list != null && list.size() > 0){
			sum = list.size();
		}
		modelAndView.addObject("name", "baby!用户表中的数据共有"+sum+"条！");
		return modelAndView;
	}
}
