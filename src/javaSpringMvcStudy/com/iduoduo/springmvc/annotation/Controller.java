package javaSpringMvcStudy.com.iduoduo.springmvc.annotation;

import java.lang.annotation.*;

/**
 * @program: test
 * @description: controller的声明
 * @author: Mr.Li
 * @create: 2021-02-24 22:00
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Controller {
}
