package com.wangshili.service;


import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wangshili.dao.KucunDao;
import com.wangshili.pojo.Kucun;

@Service
public class TestService {
	@Autowired
	private KucunDao kucunDao;
	

	//接收消息并处理请求
	@RabbitListener(queuesToDeclare = @Queue("miaosha"))
	public void receive1(Integer userId) { 
		
		//先判断库存
		Integer number= kucunDao.selectById(1).getNumber();//找出库存
		
		/**
		 * 库存一般都是取到缓存中等抢购结束后再进行数据库的增删改，这里不用redis，所以会有些麻烦
		 */
		if(number<1) {
			System.out.println("用户"+userId+"抢购失败");
			return;
		}
		number--;
		Kucun kucun = new Kucun();
		kucun.setId(1);
		kucun.setNumber(number);
		kucunDao.updateById(kucun); //将修改后的值插入
		System.out.println("用户"+userId+"抢购成功");
		/**
		 * 抢购成功后可以将用户id加入秒杀成功的表，这里省略，只打印
		 */
	}
}
