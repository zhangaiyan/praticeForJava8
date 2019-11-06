package demo;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhangchaoyue
 * @date 2019/11/5
 */
public class Demo2 {

    public static void main(String[] args) {

        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");

        //Java8之前
        for (String feature : features){
            System.out.print(feature + "\t");
        }
        System.out.println();

        //Java8之后
        features.forEach(n -> System.out.print(n + "\t"));
        System.out.println();

        //使用Java8的方法引用，方法引用由::双冒号操作符标示
        features.forEach(System.out::println);

    }
}
