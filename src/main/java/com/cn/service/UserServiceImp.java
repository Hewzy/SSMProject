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
    // 注入UserDao
    @Autowired
    private UserDao userDao;

    // 通过用户名和密码查询用户
    public User findUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User retunUser = userDao.findUser(user);
        return retunUser;
    }

    //通过用户名查询用户并返回
    public User findByName(String username) {
        // TODO Auto-generated method stub
        User user = new User();
        user.setUsername(username);
        User user2 = userDao.findByName(username);
        return user2;
    }

    //传入用户名，手机号，密码，添加用户信息
    public int addUser(String username, String number, String password) {
        // TODO Auto-generated method stub
        int t = userDao.addUser(username, number, password);
        return t;
    }

    //通过密码查询是否存在，返回值用于判断
    public int findByPassword(String password) {
        int t = userDao.findByPassword(password);
        if (t != 0) {
            return 1;
        } else {
            return 0;
        }
    }

    //列出所有的消息体消息内容
    public ArrayList<message> findall() {
        return (ArrayList<message>) this.userDao.findall();
    }

    //根据id删除消息体内容
    public void delete(int id) {
        // TODO Auto-generated method stub
        userDao.delete(id);
    }

    //根据传入的参数修改消息体内容
    public void update(int id, String title, String time, String content) {
        // TODO Auto-generated method stub
        userDao.update(id, title, time, content);
    }

    //根据id查找消息体内容
    public message findMessageById(int id) {
        // TODO Auto-generated method stub
        return userDao.findMessageById(id);
    }

    public void addMessageById(String title, String time, String content) {
        // TODO Auto-generated method stub
        userDao.addMessageById(title, time, content);
    }
}