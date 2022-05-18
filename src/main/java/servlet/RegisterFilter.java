package servlet;

import Dao.MybatisMapper;
import Dao.SqlSessionor;
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
 * @ClassName 注册过滤器
 * @Description
 * @Author:liyunzhi
 * @Date
 * @Version 1.0
 **/
@WebFilter("/reguser")
public class RegisterFilter implements Filter {
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
        System.out.println("=====================注册中======================");
        //===========================接收请求体数据=============================
        String len = null;
        String message ="";
        BufferedReader body = servletRequest.getReader();
        while((len = body.readLine())!=null){
            message += len;
        }
        System.out.println(message);
        //json字符串转java对象
        User bodyuser= JSON.parseObject(message, User.class);
        System.out.println(bodyuser);
        //===========================接收请求体数据结束===========================
        // //把请求体中username取出来，为类与数据库中username比较，若相同，则注册用户名重复。
        String bodyusername = bodyuser.getUsername();
        String bodypassword = bodyuser.getPassword();

        //数据库mapper拿出来
        userinfoMapper reguserbatisor = MybatisMapper.mybatisor();
        List<User> users = reguserbatisor.selectAll();

        if(bodyusername.equals("")||bodypassword.equals("")){
            System.out.println("用户名或密码不能为空");
        }else {
            for (User user : users) {
                System.out.println("1");
                if(bodyusername.equals(user.getUsername())){
                    System.out.println("用户名重复");
                    System.out.println("==========注册失败==========");
                }else if(!bodyusername.equals(user.getUsername())){
                    System.out.println("注册中");
                    if((users.size() - 1 == users.indexOf(user))){
                        //这里避免数据重复插入数据库
                        reguserbatisor.insertOne(bodyuser);
                        //事务提交
                        filterChain.doFilter(servletRequest, servletResponse);
                    }
                }else {
                    System.out.println("未知错误，注册失败");
                }
            }
        }


    }

    @Override
    public void destroy() {

    }
}
