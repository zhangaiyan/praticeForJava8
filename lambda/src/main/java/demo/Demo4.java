package demo;

import bean.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author zhangchaoyue
 * @date 2019/11/5
 */
public class Demo4 {

    public static void main(String[] args) {

//        predicate();
//        function();
//        supplier();
        consume();
    }

    /**
     * 断言接口
     */
    private static void predicate() {
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        Predicate<String> startWithJ = (n) -> n.startsWith("J");
        Predicate<String> fourLetterLong = (n) -> n.length() == 4;
        //将两个或多个Predicate合并成一个
        languages.stream().filter(startWithJ.and(fourLetterLong)).forEach(n -> System.out.print(n + "\t"));
    }

    /**
     * 功能接口
     */
    private static void function() {
        Function<String, Integer> toInteger = s -> Integer.valueOf(s);
        //compose在调用函数执行前执行
        Function<Object, Integer> before = toInteger.compose(String::valueOf);
        System.out.println(before.apply(123));
        //andThen在调用函数执行之后执行
        Function<String, String> after = toInteger.andThen(String::valueOf);
        //apply根据入参获得结果
        System.out.println(after.apply("456"));
    }

    /**
     * 供应接口
     */
    private static void supplier() {
        Supplier<Employee> supplier = Employee::new;
        System.out.println(supplier.get());
    }

    /**
     * 消费接口
     */
    private static void consume() {
        Consumer<Employee> greeter = (p) -> System.out.println("Hello:" + p.getName());
        Consumer<Employee> printAge = greeter.andThen((p) -> System.out.println(p.getAge()));
        greeter.accept(new Employee("张三", 25));
        printAge.accept(new Employee("张三", 25));
    }
}
