package com.sakanal.mvc.demo2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/request")
public class RequestMapperController {
//    @RequestMapping("/testRequestMapping")
    @RequestMapping(
            value = {"/testRequestMapping","/test"},
            method = {RequestMethod.POST}
     ) public String toSuccess(){
        System.out.println("toSuccess");
        return "success";
    }

    /**
     *  1、对于处理指定请求方式的控制器方法，SpringMVC中提供了@RequestMapping的派生注解
     *      处理get请求的映射-->@GetMapping
     *      处理post请求的映射-->@PostMapping
     *      处理put请求的映射-->@PutMapping
     *      处理delete请求的映射-->@DeleteMapping
     *  2、常用的请求方式有get，post，put，delete
     *      但是目前浏览器只支持get和post，若在form表单提交时，为method设置了其他请求方式的字符串（put或delete），则按照默认的请求方式get处理
     *      若要发送put和delete请求，则需要通过spring提供的过滤器HiddenHttpMethodFilter，在RESTful部分会讲到
     */
    @GetMapping("/testRequestMapping")
    public String testGetMapping(){
        System.out.println("testGetMapping");
        return"success";
    }
    @PutMapping("/testPut")
    public String testPut(){
        return "success";
    }

    /**
     * @RequestMapping注解的params属性通过请求的请求参数匹配请求映射
     * @RequestMapping注解的params属性是一个字符串类型的数组，可以通过四种表达式设置请求参数和请求映射的匹配关系
     *  "param"：要求请求映射所匹配的请求必须携带param请求参数
     *  "!param"：要求请求映射所匹配的请求必须不能携带param请求参数
     *  "param=value"：要求请求映射所匹配的请求必须携带param请求参数且param=value
     *  "param!=value"：要求请求映射所匹配的请求必须携带param请求参数但是param!=value
     *  若当前请求满足@RequestMapping注解的value和method属性，但是不满足params属性，此时
     *  页面回报错400：Parameter conditions "username, password!=123456" not met for actual
     *  request parameters: username={admin}, password={123456}
     *
     *  @RequestMapping注解的headers属性通过请求的请求头信息匹配请求映射
     *  @RequestMapping注解的headers属性是一个字符串类型的数组，可以通过四种表达式设置请求头信息和请求映射的匹配关系
     *  "header"：要求请求映射所匹配的请求必须携带header请求头信息
     *  "!header"：要求请求映射所匹配的请求必须不能携带header请求头信息
     *  "header=value"：要求请求映射所匹配的请求必须携带header请求头信息且header=value
     *  "header!=value"：要求请求映射所匹配的请求必须携带header请求头信息且header!=value
     *  若当前请求满足@RequestMapping注解的value和method属性，但是不满足headers属性，此时页面显示404错误，即资源未找到
     */
    @RequestMapping(value = "/testParamsAndHeaders",params = {"username","password=123456"})
    public String testParamsAndHeaders(){
        return "success";
    }

    /**
     * ？：表示任意的单个字符
     * *：表示任意的0个或多个字符
     * **：表示任意的一层或多层目录
     *  **只能用于中间目录，不然就只表示普通的两个**；eg：a**a≈a*a
     */
    @RequestMapping(value = {"/testAnt1?","testAnt2*","/**/testAnt3"})
    public String testAnt(){
        return "success";
    }

    /**
     *  SpringMVC支持路径中的占位符（重点）
     *      原始方式：/deleteUser?id=1
     *      rest方式：/deleteUser/1
     *      SpringMVC路径中的占位符常用于RESTful风格中，
     *      当请求路径中将某些数据通过路径的方式传输到服务器中，就可以在相应的@RequestMapping注解的value属性中通过占位符{xxx}表示传输的数据，
     *      在通过@PathVariable注解，将占位符所表示的数据赋值给控制器方法的形参
     */
    @RequestMapping("/testPath/{id}/{username}")
    public String testPath(@PathVariable("id")String id,@PathVariable("username")String username){
        System.out.println("id = " + id);
        System.out.println("username = " + username);
        return "success";
    }
}
