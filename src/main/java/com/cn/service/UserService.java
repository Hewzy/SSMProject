package com.cn.service;

import java.util.ArrayList;

import com.cn.pojo.User;
import com.cn.pojo.message;

public interface UserService {
	// 根据用户名 密码查询用户
	public User findUser(String username ,String password);
	// 根据用户名查询用户
	public User findByName(String username);
	// 添加用户，返回0为添加不成功
	public int addUser(String username,String number,String password);
	// 根据密码查询是否正确
	public int findByPassword(String password);
	// 查询所有的消息体消息
	public ArrayList<message> findall();
	// 根据id删除消息体的消息
	public void delete(int id);
	//// 根据id更新消息体的消息内容
	public void update(int id,String title,String time,String content);
	//根据id返回消息体的消息
	public message findMessageById(int id);
	//添加消息内容
	public void addMessageById(String title, String time, String content);
}
