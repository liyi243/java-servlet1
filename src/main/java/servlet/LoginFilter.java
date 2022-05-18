package servlet;

import Dao.MybatisMapper;
import com.alibaba.fastjson.JSON;
import mapper.userinfoMapper;
import user.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * @ClassName 登录取过滤器
 * @Description
 * @Author:liyunzhi
 * @Date
 * @Version 1.0
 **/
@WebFilter("/login")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("=====================跨域=======================");
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        System.out.println("=====================登录中======================");
        //===========================接收请求体数据=============================
        String len = null;
        String message = "";
        BufferedReader body = servletRequest.getReader();
        while ((len = body.readLine()) != null) {
            message += len;
        }
        System.out.println(message);
        //json字符串转java对象
        User bodyuser = JSON.parseObject(message, User.class);
        System.out.println(bodyuser);
        //===========================接收请求体数据结束===========================
        //把请求体中username取出来，为类与数据库中username比较，若相同，则注册用户名重复。
        String bodyusername = bodyuser.getUsername();
        String bodypassword = bodyuser.getPassword();
        if (bodyusername.equals("") || bodypassword.equals("")) {
            System.out.println("用户名或密码不能为空");
        }
        //数据库mapper拿出来
        userinfoMapper reguserbatisor = MybatisMapper.mybatisor();
        List<User> users = reguserbatisor.selectAll();
        System.out.println(users);
        for (int i = 0; i < users.size(); i++) {

            if (i < users.size() - 1) {
                System.out.println(users.get(i).getUsername());
                if (!bodyusername.equals(users.get(i).getUsername())) {
                    System.out.println("查询中");
                    //System.out.println(users.size());
                } else if (bodyusername.equals(users.get(i).getUsername())) {
                    if (!bodypassword.equals(users.get(i).getPassword())) {
                        System.out.println("密码错误,请重试");
                    } else {
                        //登陆成功
                        filterChain.doFilter(servletRequest, servletResponse);
                    }
                }
            }else {
                //System.out.println();
                if (!bodyusername.equals(users.get(i).getUsername())) {
                    System.out.println("用户名不存在");
                } else if (bodyusername.equals(users.get(i).getUsername())) {
                    if (!bodypassword.equals(users.get(i).getPassword())) {
                        System.out.println("密码错误,请重试");
                    } else {
                        //登陆成功
                        filterChain.doFilter(servletRequest, servletResponse);
                    }
                }
            }
                // for (User user : users) {
                //     if(!(bodyusername.equals(user.getUsername()))){
                //         System.out.println("查询中");
                //     }else if(bodyusername.equals(user.getUsername())){
                //         if(!bodypassword.equals(user.getPassword())){
                //             System.out.println("密码错误,请重试");
                //         }else {
                //             //登陆成功
                //             filterChain.doFilter(servletRequest, servletResponse);
                //         }
                //     }else {
                //         System.out.println("未知错误");
                //     }
                // }



        }
    }
    @Override
    public void destroy() {

    }
}
