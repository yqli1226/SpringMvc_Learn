package javaSpringMvcStudy.com.iduoduo.springmvc.Controller;

import javaSpringMvcStudy.com.iduoduo.springmvc.annotation.Controller;
import javaSpringMvcStudy.com.iduoduo.springmvc.annotation.RequestMapping;

/**
 * @program: test
 * @description:
 * @author: Mr.Li
 * @create: 2021-02-24 23:25
 **/
@Controller
@RequestMapping
public class IndexController {
    @RequestMapping
    public void index(){
        System.out.println("index->index");
    }
}
