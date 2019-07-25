package com.cn.service;

import java.util.ArrayList;

import com.cn.pojo.User;
import com.cn.pojo.message;

public interface UserService {
	// �����û��� �����ѯ�û�
	public User findUser(String username ,String password);
	// �����û�����ѯ�û�
	public User findByName(String username);
	// ����û�������0Ϊ��Ӳ��ɹ�
	public int addUser(String username,String number,String password);
	// ���������ѯ�Ƿ���ȷ
	public int findByPassword(String password);
	// ��ѯ���е���Ϣ����Ϣ
	public ArrayList<message> findall();
	// ����idɾ����Ϣ�����Ϣ
	public void delete(int id);
	//// ����id������Ϣ�����Ϣ����
	public void update(int id,String title,String time,String content);
	//����id������Ϣ�����Ϣ
	public message findMessageById(int id);
	//�����Ϣ����
	public void addMessageById(String title, String time, String content);
}
