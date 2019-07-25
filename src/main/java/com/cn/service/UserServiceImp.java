package com.cn.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cn.dao.UserDao;
import com.cn.pojo.User;
import com.cn.pojo.message;


@Service
@Transactional
public class UserServiceImp implements UserService {
    // ע��UserDao
    @Autowired
    private UserDao userDao;

    // ͨ���û����������ѯ�û�
    public User findUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User retunUser = userDao.findUser(user);
        return retunUser;
    }

    //ͨ���û�����ѯ�û�������
    public User findByName(String username) {
        // TODO Auto-generated method stub
        User user = new User();
        user.setUsername(username);
        User user2 = userDao.findByName(username);
        return user2;
    }

    //�����û������ֻ��ţ����룬����û���Ϣ
    public int addUser(String username, String number, String password) {
        // TODO Auto-generated method stub
        int t = userDao.addUser(username, number, password);
        return t;
    }

    //ͨ�������ѯ�Ƿ���ڣ�����ֵ�����ж�
    public int findByPassword(String password) {
        int t = userDao.findByPassword(password);
        if (t != 0) {
            return 1;
        } else {
            return 0;
        }
    }

    //�г����е���Ϣ����Ϣ����
    public ArrayList<message> findall() {
        return (ArrayList<message>) this.userDao.findall();
    }

    //����idɾ����Ϣ������
    public void delete(int id) {
        // TODO Auto-generated method stub
        userDao.delete(id);
    }

    //���ݴ���Ĳ����޸���Ϣ������
    public void update(int id, String title, String time, String content) {
        // TODO Auto-generated method stub
        userDao.update(id, title, time, content);
    }

    //����id������Ϣ������
    public message findMessageById(int id) {
        // TODO Auto-generated method stub
        return userDao.findMessageById(id);
    }

    public void addMessageById(String title, String time, String content) {
        // TODO Auto-generated method stub
        userDao.addMessageById(title, time, content);
    }
}