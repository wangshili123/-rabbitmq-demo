package com.wangshili.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 使用jemeter模拟并发，超过100就行，因为库存是100
 * @author Administrator
 *
 */
@Controller
public class TestController {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	//模拟用户id
	private static Integer userId=0;
	
	@RequestMapping("/miaosha")
	@ResponseBody
	public String miaosha() {
		userId++;//模拟用户id
		
		//发布消息到消息队列中
		rabbitTemplate.convertAndSend("miaosha", userId);
		
		return "ok";
	}
}
