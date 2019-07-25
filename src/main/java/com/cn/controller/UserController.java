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
    // ����ע��
    @Autowired
    private UserService userService;

    //��ת����½ҳ��
    @RequestMapping("/login")
    public String toLogin() {
        return "login";
    }

    // ��ת��ע��ҳ��
    @RequestMapping("/register")
    public String toRegister() {
        return "register";
    }

    //��ת�������Ϣҳ��
    @RequestMapping("/add")
    public String addMessage() {
        return "add";
    }

    //ͨ���û��������û���Ϣ
    @RequestMapping("/checkName")
    @ResponseBody
    public User register(String username) {

        User user2 = userService.findByName(username);

        return user2;
    }

    // У����֤���Ƿ���ȷ
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

    // �ж�ע����û�гɹ����ɹ�����ת��successҳ�棬ʧ����ת��failҳ��
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

    //����û����������Ƿ����
    @RequestMapping("/checkUser")
    @ResponseBody
    public int checkUser(String username, String password) {
        password = MD5Utils.getMD5(password);
        User user = userService.findByName(username);
        int t = userService.findByPassword(password);
        // 0:�û������� 1�����벻���� 2���û�������ȷ
        if (user == null) {
            return 0;
        } else if (t == 0) {
            return 1;
        } else {
            return 2;
        }
    }


    //��ѯ��ʾ������Ϣ����Ϣ
    @RequestMapping("/messageAll")
    public ModelAndView handleMessage() {
        ModelAndView mv = new ModelAndView("message");
        ArrayList<message> list = this.userService.findall();
        mv.addObject("list", list);
        return mv;
    }

    //����Idɾ����Ϣ������
    @RequestMapping("/delete")
    public String deletMessage(int id) {
        userService.delete(id);
        return "redirect:messageAll.action";
    }


    //����Id�����û���Ϣ
    @RequestMapping("/update")
    public String update(int id, String title, String time, String content) {
        System.out.println(id + " " + title + " " + time + " " + content);
        userService.update(id, title, time, content);
        return "redirect:messageAll.action";
    }


    // ����Id��ת����Ӧ�ı༭ҳ��
    @RequestMapping("/editMessage")
    public ModelAndView editMessage(int id) {
        //�������ݵ�edit��ͼ
        ModelAndView mv = new ModelAndView("edit");
        message mes = new message();
        mes = userService.findMessageById(id);
        mv.addObject("mes", mes);
        return mv;
    }

    //����id��ת����Ӧ����Ϣ�༭ҳ��
    @RequestMapping("/addMessage")
    public String addMessage(String title, String time, String content) {
        userService.addMessageById(title, time, content);
        return "redirect:messageAll.action";

    }
}
