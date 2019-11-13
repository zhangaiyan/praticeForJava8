package demo;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author zhangchaoyue
 * @date 2019/11/5
 */
public class Demo3 {

    public static void main(String[] args) {

        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        System.out.println("Languages which starts with J :");
        filterByStream(languages, (str) -> str.startsWith("J"));

        System.out.println("Languages which ends with a ");
        filterByStream(languages, (str) -> str.endsWith("a"));

        System.out.println("Print all languages :");
        filterByStream(languages, (str) -> true);

        System.out.println("Print no language : ");
        filterByStream(languages, (str) -> false);

        System.out.println("Print language whose length greater than 4:");
        filterByStream(languages, (str) -> str.length() > 4);

    }

    /**
     * 传统过滤方式
     * @param names
     * @param condition
     */
    public static void filter(List<String> names, Predicate<String> condition) {
        for (String name : names) {
            if (condition.test(name)) {
                System.out.println(name + " ");
            }
        }
    }

    //使用stream API的过滤方法进行过滤
    public static void filterByStream(List<String> names, Predicate<String> condition) {
        names.stream().filter((name) -> (condition.test(name))).forEach((name) -> {
            System.out.println(name + " ");
        });
    }

}
