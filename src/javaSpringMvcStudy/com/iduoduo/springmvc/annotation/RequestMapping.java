package javaSpringMvcStudy.com.iduoduo.springmvc.annotation;

import java.lang.annotation.*;

/**
 * @program: test
 * @description: requestMapping的声明
 * @author: Mr.Li
 * @create: 2021-02-24 22:02
 **/
@Documented
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    /*
    * @return
    * */
    String value() default "";
}
