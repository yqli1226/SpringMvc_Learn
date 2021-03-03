package javaSpringMvcStudy.com.iduoduo.springmvc.Controller;

import javaSpringMvcStudy.com.iduoduo.springmvc.annotation.Controller;
import javaSpringMvcStudy.com.iduoduo.springmvc.annotation.RequestMapping;

/**
 * @program: test
 * @description:
 * @author: Mr.Li
 * @create: 2021-02-24 22:09
 **/
@Controller
@RequestMapping("test")
public class TestController {
    @RequestMapping
    public String index(){
        System.out.println("test -> index");
        return "";
    }
    @RequestMapping("index1")
    public String index1(){
        System.out.println("test -> index1");
        return "";
    }
}
