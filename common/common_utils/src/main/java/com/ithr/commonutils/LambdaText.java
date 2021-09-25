package com.ithr.commonutils;

import com.ithr.commonutils.text.MyPredicate;
import com.ithr.commonutils.text.MyPredicateAgeImpl;
import com.ithr.commonutils.text.MyPredicateMonempl;
import org.junit.Test;

import java.util.*;

/**
 * @author com.hr.Text
 * @date 2020/12/19 - 18:26
 */

public class LambdaText {

    @Test
    //译名
    public void test1() {
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }

        };
        TreeSet<Integer> ts=new TreeSet<>(com);
        System.out.println(ts);
    }


    @Test
    public void test2(){
        Comparator<Integer> com=(x,y) ->  Integer.compare(x,y);

        //获取年龄大于35的
    }

    List<LambdaEntity> list=Arrays.asList(
            new LambdaEntity("罗东东",18,9999.6),
            new LambdaEntity("李启超",38,5555.6),
            new LambdaEntity("我爱罗",50,6666.6),
            new LambdaEntity("阿秀看了",16,3333.6),
            new LambdaEntity("爱情买卖",8,7777.6)
    );

    //优化方式1(1)  策略模式
    @Test
    public void test4() {
        System.out.println("开始===============================================================");
        List<LambdaEntity> lambdaEntities = filterEmployee(list, new MyPredicateAgeImpl());
        for (LambdaEntity lambdaEntity : lambdaEntities) {
            System.out.println(lambdaEntity);
        }
        System.out.println("按年龄↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑");

        List<LambdaEntity> lambdaEntities1 = filterEmployee(list, new MyPredicateMonempl());
        for (LambdaEntity lambdaEntity : lambdaEntities1) {
            System.out.println(lambdaEntity);
        }
        System.out.println("结束===============================================================");

    }

    //优化方式1
    public List<LambdaEntity> filterEmployee(List<LambdaEntity> list, MyPredicate<LambdaEntity> mp){
        List<LambdaEntity> emps=new ArrayList<>();

        for (LambdaEntity lambdaEntity : list) {
            if(mp.text(lambdaEntity)){
                emps.add(lambdaEntity);
            }
        }
         return emps;
    }


    //优化方式2
    @Test
    public void test5(){
      List<LambdaEntity> list1= filterEmployee(list, new MyPredicate<LambdaEntity>() {
            @Override
            public boolean text(LambdaEntity lambdaEntity) {
                return lambdaEntity.getAge()>=30;
            }
        });

        for (LambdaEntity lambdaEntity : list1) {
            System.out.println(lambdaEntity);
        }
    }

    //优化方式3 lambda表达式
    @Test
    public void test6(){
        List<LambdaEntity> lambdaEntities = filterEmployee(list, (e) -> e.getAge() > 30);
        lambdaEntities.forEach(System.out::println);
    }

    //优化方式4
    @Test
    public void test7(){
        list.stream()
                .filter((e) -> e.getMone()>=4000)
                .limit(2)
                .forEach(System.out::println);

        System.out.println("-===========================================");

        list.stream()
//                .map(list -> list.getName())
                .map(LambdaEntity::getAge)
                .forEach(System.out::println);
    }
}
