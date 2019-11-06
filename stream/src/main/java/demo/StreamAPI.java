package demo;

import bean.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author zhangchaoyue
 * @date 2019/11/6
 */
public class StreamAPI {

    public static void main(String[] args) {
//        createStream();
        screen();

    }

    /**
     * 创建流
     */
    private static void createStream() {
        //1、通过Collection系列集合提供的stream()或parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        //2、通过Arrays中的静态方法stream()获取数组流
        Employee[] employees = new Employee[10];
        Stream<Employee> stream2 = Arrays.stream(employees);

        //3、通过Stream类中的静态方法of()
        Stream<String> stream3 = Stream.of("aa", "bb", "cc");
        stream3.forEach(System.out::println);
        System.out.println("-------------");

        //4、迭代方式创建无限流
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);
        stream4.limit(10).forEach(System.out::println);
        System.out.println("--------------");


        //5、生成方式创建无限流
        Stream.generate(() -> Math.random()).limit(5).forEach(System.out::println);
        System.out.println();
    }

    /**
     * 筛选和切片
     */
    private static void screen() {
        List<Employee> employees = createEmployeeList();
        //1、filter——接收lambda，从流中排除某些元素
        employees.stream().filter((e) -> e.getAge() > 18).forEach(System.out::println);
        System.out.println("--------------");
        //2、limit——截断流，使其元素不超过给定数量
        employees.stream().filter((e) -> e.getAge() > 18).limit(4).forEach(System.out::println);
        System.out.println("--------------");
        //3、skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流
        employees.stream().filter((e) -> e.getAge() > 18).skip(2).forEach(System.out::println);
        System.out.println("--------------");
        //4、distinct——筛选，因为通过流所生成元素的 hashCode() 和 equals() 去除重复元素，所以对象要重写hashCode跟equals方法
        employees.stream().distinct().forEach(System.out::println);
        System.out.println("--------------");
    }


    /**
     * 常见demo需要使用的集合
     *
     * @return
     */
    private static List<Employee> createEmployeeList() {
        List<Employee> employees = Arrays.asList(
                new Employee(102, "李四", 59, 6666.66),
                new Employee(101, "张三", 18, 9999.99),
                new Employee(103, "王五", 28, 3333.33),
                new Employee(104, "赵六", 8, 7777.77),
                new Employee(104, "赵六", 8, 7777.77),
                new Employee(104, "赵六", 8, 7777.77),
                new Employee(105, "田七", 38, 5555.55)
        );
        return employees;
    }
}
