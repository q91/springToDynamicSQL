package com.lll.ui;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.kings.em.ccl.datastructure.impl.BaseDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lll.services.UserService;
import com.lll.utils.DataSourceUtils;
import com.lll.utils.DynamicDatasource;

@Controller
@RequestMapping("/user")
@ResponseBody
public class UserUI {

	@Resource
	private UserService userServices;

	private DynamicDatasource dynamicDatasource = new DynamicDatasource();
	
	@PostMapping
	@RequestMapping(value = "/add3", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String addtest3(HttpServletRequest request) {
        System.out.println("ok");
        //增加数据源
        dynamicDatasource.addDataSource("dataSourceNew2", "**.**.*.***", 3306, "test3", "root", "Mysql#2019");
        //指定使用的数据源
        DataSourceUtils.setDbKey("dataSourceNew2");
       
        //执行sql
        BaseDto dto = new BaseDto();
		dto.put("rid", "33");
		dto.put("name", "test33");
		dto.put("a", "33");
		userServices.addUser(dto);
		
		return "";
    }    
	
	@PostMapping
	@RequestMapping(value = "/add4", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String addtest4(HttpServletRequest request) {
        BaseDto dto = new BaseDto();
		dto.put("rid", "44");
		dto.put("name", "test44");
		dto.put("a", "44");
		
		//指定使用的数据源
		DataSourceUtils.setDbKey("dataSourceNew2");
		userServices.addUser(dto);
		return "";
    }    
}
