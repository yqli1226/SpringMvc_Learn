package javaSpringMvcStudy.com.iduoduo.springmvc;

import javaSpringMvcStudy.com.iduoduo.springmvc.mvc.Mvc;


/**
 * @program: test
 * @description:
 * @author: Mr.Li
 * @create: 2021-02-24 23:26
 **/
public class Main {
    static {
        String path = Main.class.getResource("").getPath();
        String packageName = Main.class.getPackage().getName();
        Mvc.scanner(path,packageName);
    }

    public static void main(String[] args) {
        Mvc.exec("","");
        Mvc.exec("test","index1");
        Mvc.exec("test","");
        System.out.println("Hello World!");
    }
}
