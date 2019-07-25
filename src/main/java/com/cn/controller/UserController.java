package com.cn.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cn.pojo.User;
import com.cn.pojo.message;
import com.cn.service.UserService;

@Controller
public class UserController {
    // 依赖注入
    @Autowired
    private UserService userService;

    //跳转到登陆页面
    @RequestMapping("/login")
    public String toLogin() {
        return "login";
    }

    // 跳转到注册页面
    @RequestMapping("/register")
    public String toRegister() {
        return "register";
    }

    //跳转到添加消息页面
    @RequestMapping("/add")
    public String addMessage() {
        return "add";
    }

    //通过用户名查找用户信息
    @RequestMapping("/checkName")
    @ResponseBody
    public User register(String username) {

        User user2 = userService.findByName(username);

        return user2;
    }

    // 校验验证码是否正确
    @RequestMapping("/checkSafeCode")
    @ResponseBody
    public int checkSafeCode(String safeCode, HttpSession session) {
        String safeCode2 = (String) session.getAttribute("verifyCodeValue");
        int i = 0;
        if (safeCode.equals(safeCode2)) {
            i = 1;
            return i;
        }
        return i;
    }

    // 判断注册有没有成功，成功则跳转到success页面，失败跳转到fail页面
    @RequestMapping("/register01")
    public String register(String username, String number, String password) {
        password = MD5Utils.getMD5(password);
        if ((username == null || username == "") || (number == null || number == "") || (password == null || password == "")) {
            return "fail";
        } else {
            int t = userService.addUser(username, number, password);
            if (t != 0) {
                return "success";
            } else return "fail";
        }
    }

    //检查用户名和密码是否存在
    @RequestMapping("/checkUser")
    @ResponseBody
    public int checkUser(String username, String password) {
        password = MD5Utils.getMD5(password);
        User user = userService.findByName(username);
        int t = userService.findByPassword(password);
        // 0:用户不存在 1：密码不存在 2：用户密码正确
        if (user == null) {
            return 0;
        } else if (t == 0) {
            return 1;
        } else {
            return 2;
        }
    }


    //查询显示所有消息体信息
    @RequestMapping("/messageAll")
    public ModelAndView handleMessage() {
        ModelAndView mv = new ModelAndView("message");
        ArrayList<message> list = this.userService.findall();
        mv.addObject("list", list);
        return mv;
    }

    //根据Id删除消息体内容
    @RequestMapping("/delete")
    public String deletMessage(int id) {
        userService.delete(id);
        return "redirect:messageAll.action";
    }


    //根据Id更新用户信息
    @RequestMapping("/update")
    public String update(int id, String title, String time, String content) {
        System.out.println(id + " " + title + " " + time + " " + content);
        userService.update(id, title, time, content);
        return "redirect:messageAll.action";
    }


    // 根据Id跳转到相应的编辑页面
    @RequestMapping("/editMessage")
    public ModelAndView editMessage(int id) {
        //传入数据到edit视图
        ModelAndView mv = new ModelAndView("edit");
        message mes = new message();
        mes = userService.findMessageById(id);
        mv.addObject("mes", mes);
        return mv;
    }

    //根据id跳转到相应的消息编辑页面
    @RequestMapping("/addMessage")
    public String addMessage(String title, String time, String content) {
        userService.addMessageById(title, time, content);
        return "redirect:messageAll.action";

    }
}
