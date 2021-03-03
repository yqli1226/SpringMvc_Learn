package javaSpringMvcStudy.com.iduoduo.springmvc.mvc;

import javaSpringMvcStudy.com.iduoduo.springmvc.annotation.Controller;
import javaSpringMvcStudy.com.iduoduo.springmvc.annotation.RequestMapping;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;

/**
 * @program: test
 * @description: mvc类
 * @author: Mr.Li
 * @create: 2021-02-24 22:11
 **/
public class Mvc {
    private static HashMap<String, Map<String, Method>> map = new HashMap<>();
    private static HashMap<String, Object> objMap = new HashMap<>();
    public static void exec(String classPath, String methodPath){
        if(objMap.get(classPath) == null){
            System.out.println("404,not find this class");
        }else{
            if(map.get(classPath).get(methodPath) == null){
                System.out.println("404, not find this mothed");
            }else{
                try{
                    map.get(classPath).get(methodPath).invoke(objMap.get(classPath));
                }catch (IllegalAccessException e){
                    e.printStackTrace();
                }catch (InvocationTargetException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void scanner(String path, String packageName){
        List<String> paths = traverseFolder2(path);
        for (String p : paths) {
            p = p.substring(path.length() - 1);
            try{
                String className = packageName + "." + p.replaceAll(Matcher.quoteReplacement(File.separator),".");
                String replace = className.replace(".class","");
                Class<?> cl = ClassLoader.getSystemClassLoader().loadClass(replace);
                if(iSController(cl)){
                    if(isRequestMapping(cl)){
                        RequestMapping requestMapping = getRequestMapping(cl);
                        if(map.containsKey(requestMapping.value())){
                            throw new RuntimeException("类多注解值:" + requestMapping.value());
                        }else{
                            map.put(requestMapping.value(), new HashMap<>());
                            objMap.put(requestMapping.value(), cl.newInstance());
                        }
                        Method[] declaredMethods = cl.getDeclaredMethods();
                        for (Method declaredMethod : declaredMethods) {
                            if(isRequestMapping(declaredMethod)){
                                RequestMapping mapping = getRequestMapping(declaredMethod);
                                if(map.get((requestMapping.value())).containsKey(mapping.value())){
                                    throw new RuntimeException("方法多值注解：" + requestMapping.value());
                                }else{
                                    map.get(requestMapping.value()).put(mapping.value(),declaredMethod);
                                }
                            }
                        }
                    }else{
                        throw new RuntimeException("类无requestMapping");
                    }
                }
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }catch (IllegalAccessException e){
                e.printStackTrace();
            }catch (InstantiationException e){
                e.printStackTrace();
            }
        }
    }

    private static boolean iSController(Class cl){
        Annotation annotation = cl.getAnnotation(Controller.class);
        if(annotation!=null){
            return true;
        }
        return false;
    }

    private static boolean isRequestMapping(Class cl){
        Annotation annotation = cl.getAnnotation(RequestMapping.class);
        if(annotation != null){
            return  true;
        }
        return false;
    }

    private static boolean isRequestMapping(Method method){
        Annotation annotation = method.getAnnotation(RequestMapping.class);
        if(annotation != null){return true;}
        return false;
    }

    private static RequestMapping getRequestMapping(Class cl){
        Annotation annotation = cl.getAnnotation(RequestMapping.class);
        if(annotation instanceof  RequestMapping){
            return  (RequestMapping) annotation;
        }
        return null;
    }

    private static RequestMapping getRequestMapping(Method method){
        Annotation annotation = method.getAnnotation(RequestMapping.class);
        if(annotation instanceof  RequestMapping){
            return  (RequestMapping) annotation;
        }
        return null;
    }
    
    private static List<String> traverseFolder2(String path){
        File file = new File(path);
        List<String> classFiles = new ArrayList<>();
        if(file.exists()){
            LinkedList<File> list = new LinkedList<>();
            File[] files = file.listFiles();
            for (File file1 : files) {
                if(file1.isDirectory()){
                    list.add(file1);
                }else {
                    classFiles.add(file1.getAbsolutePath());
                }
            }
            File temp_file;
            while(!list.isEmpty()){
                temp_file = list.removeFirst();
                files = temp_file.listFiles();
                for (File file1 : files) {
                    if(file1.isDirectory()){
                        list.add(file1);
                    }else {
                        classFiles.add(file1.getAbsolutePath());
                    }
                }
            }
        }else{

        }
        return classFiles;
    }
}


