package com.sakanal.mvc.demo2.controller;

import com.sakanal.mvc.demo2.pojo.User;
import com.sakanal.mvc.demo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller
public class ParamController {
    @Autowired
    private UserService userService;

    @RequestMapping("/testServletAPI")
    //形参位置的request表示当前请求
    public String testServletAPI(HttpServletRequest request){
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username="+username+";password="+password);
        return "success";
    }

    @RequestMapping("/testParam")
    //请求参数名与形参名保持一致，会自动赋值
    //若请求参数中出现多个同名的请求参数，可以在控制器方法的形参位置设置字符串类型或字符串数组接收此请求参数
    //若使用字符串类型的形参，最终结果为请求参数的每一个值之间使用逗号进行拼接
    /**
     * @RequestParam是将请求参数和控制器方法的形参创建映射关系
     * @RequestParam注解一共有三个属性：
     *  value：指定为形参赋值的请求参数的参数名
     *  required：设置是否必须传输此请求参数，默认值为true
     *      若设置为true时，则当前请求必须传输value所指定的请求参数，
     *      若没有传输该请求参数，且没有设置defaultValue属性，则页面报错400：Required String parameter 'xxx' is not present；
     *      若设置为false，则当前请求不是必须传输value所指定的请求参数，
     *      若没有传输，则注解所标识的形参的值为null
     *      注意这里是要求请求中是否有这个参数,而不是参数是否非空
     *      可以传空：/springMVC/testParam?user_name=&password=123456&hobby=C%2B%2B&hobby=JAVA基础编程
     *  defaultValue：不管required属性值为true或false，当value所指定的请求参数没有传输或传输的值为""时，则使用默认值为形参赋值
     */
    public String testParam(
            @RequestParam(value = "user_name", required = false,defaultValue = "sakanal") String username,
            String password,
            String[] hobby,
            @RequestHeader("Host") String host,
            @CookieValue("JSESSIONID") String JSESSIONID
    ){
        System.out.println("username="+username+";password="+password+";hobby="+ Arrays.toString(hobby));
        System.out.println("host = " + host);
        System.out.println("JSESSIONID = " + JSESSIONID);
        return "success";
    }

    @RequestMapping("/testPOJO")
    public String testPOJO(User user,User userInfo){
        boolean b = userService.insertUser(user);
        System.out.println(b);
//        System.out.println(user);
//        System.out.println(userInfo);
        return "success";
    }
}
