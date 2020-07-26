package com.wangshili.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("kucun")
public class Kucun implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8273559749383800093L;
	@TableId(type = IdType.AUTO)
	private Integer id;
	private Integer number; //库存
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "Kucun [id=" + id + ", number=" + number + "]";
	}
	
	
}
