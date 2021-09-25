package com.ithr.commonutils.text;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.ithr.commonutils.LambdaEntity;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author com.hr.Text
 * @date 2020/12/21 - 16:18
 */

//lamdba表达式   函数式接口

/** 方式一 ：无参数  无返回值
 * （）->System.out.println("hello Lambda"+num)
 *  方式二：有一个参数 并且没有返回值
 *  (x)-> System.out.println(x)
 *  方式三:若只有一个参数 小括号可以不写
 *  x-> System.out.println(x)
 *  方式四：有多个参数 并且Lamdba体中有多个参数
 *   Comparator<Integer> com= (x,y) ->{
 *             System.out.println("函数式接口");
 *             return Integer.compare(x,y);
 *         };
 *
 *  方式五：若lambda体中只有一条语句 大括号  很renturn都可以不写
 *
 *  方式六：Lamaba表达式的参数列表的参数类型可以省略不写 因为Jvm的编辑器通过上下文推断出数据类型
 *
 *  方式七：
 */


public class Lambda {


    @Test
    public void test1(){
        int num=3;
        Runnable r=new Runnable() {
            @Override
            public void run() {
                System.out.println("hello word"+num);
            }
        };
        r.run();
        System.out.println("=============================");

        Runnable r1=() -> System.out.println("hello Lambda"+num);
        r1.run();
    }

    @Test
    public void test2(){
        Consumer<String> consumer=x-> System.out.println(x);
        consumer.accept("我是仙女");
    }


    @Test
    public void test3(){
        Comparator<Integer> com= (x,y) ->{
            System.out.println("函数式接口");
            return Integer.compare(x,y);
        };
    }
    @Test
    public void test4(){
        Comparator<Integer> com= (x,y) -> Integer.compare(x,y);
    }


    List<LambdaEntity> list= Arrays.asList(
            new LambdaEntity("罗东东",18,9999.6),
            new LambdaEntity("李启超",38,5555.6),
            new LambdaEntity("我爱罗",50,6666.6),
            new LambdaEntity("阿秀看了",16,3333.6),
            new LambdaEntity("爱情买卖",8,7777.6)
    );
    /**
     * 排序
     */
    @Test
    public void test5(){
        Collections.sort(list,(e1,e2)->{
//            if(e1.getAge()==e2.getAge()){
//              return e1.getName().compareTo(e2.getName());
//            }else{
                return Integer.compare(e1.getAge(),e2.getAge());
//            }
        } );

        for (LambdaEntity entity : list) {
            System.out.println(entity);
        }
    }
}
