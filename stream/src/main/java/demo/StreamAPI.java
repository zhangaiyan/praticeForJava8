package demo;

import bean.Employee;
import bean.Employee2;
import bean.Employee2.Status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhangchaoyue
 * @date 2019/11/6
 */
public class StreamAPI {

    public static void main(String[] args) {
//        createStream();
//        screen();
//        mapping();
//        sort();
//        match();
//        reduce();
        collect();
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
     * 1、筛选和切片
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
     * 2、映射
     */
    private static void mapping() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        list.stream().map((str) -> str.toUpperCase()).forEach(System.out::println);
        System.out.println("--------------------");

        List<Employee> employeeList = createEmployeeList();
        employeeList.stream().map(Employee::getName).forEach(System.out::println);
        System.out.println("--------------------");

        //流中流,map是一个个流(这个流中有元素)加入流中
        list.stream().map(StreamAPI::filterCharacter).forEach(sm -> {
            sm.forEach(System.out::println);
        });
        //只有一个流,flatMap是将一个个流中的元素加入流中
        list.stream().flatMap(StreamAPI::filterCharacter).forEach(System.out::println);
    }

    /**
     * 3、排序
     */
    private static void sort() {
        List<String> list = Arrays.asList("ccc", "aaa", "bbb", "ddd", "eee");
        list.stream().sorted().forEach(System.out::println);

        System.out.println("=======定制排序=======");
        List<Employee> employeeList = createEmployeeList();
        employeeList.stream().sorted((x, y) -> {
            if (x.getAge().equals(y.getAge())) {
                return x.getName().compareTo(y.getName());
            } else {
                return Integer.compare(x.getAge(), y.getAge());
            }
        }).forEach(System.out::println);

    }

    /**
     * 4、查找与匹配
     */
    private static void match() {
        List<Employee2> employee2List = createEmployee2List();
        System.out.println(employee2List.stream().allMatch(e -> e.getStatus().equals(Status.BUSY)));
        System.out.println(employee2List.stream().anyMatch(e -> e.getStatus().equals(Status.BUSY)));
        System.out.println(employee2List.stream().noneMatch(e -> e.getStatus().equals(Status.BUSY)));
        //按工资排序并输出第一个
        System.out.println(employee2List.stream().sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())).findFirst());
        //输出任何一个工资状态为繁忙的职员
        System.out.println(employee2List.stream().filter(e -> e.getStatus().equals(Status.BUSY)).findAny());
        //返回流中元素的总个数
        System.out.println(employee2List.stream().count());
        //返回流中最大值
        System.out.println(employee2List.stream().map(Employee2::getSalary).max(Double::compareTo).get());
        //返回流中最小值
        System.out.println(employee2List.stream().map(Employee2::getSalary).min(Double::compareTo).get());
    }

    /**
     * 5、归约
     */
    private static void reduce() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(list.stream().reduce(0, (x, y) -> x + y));

        List<Employee2> employee2List = createEmployee2List();
        System.out.println(employee2List.stream().map(Employee2::getSalary).reduce(Double::sum).get());
    }

    /**
     * 6、收集
     */
    private static void collect() {
        List<Employee2> employee2List = createEmployee2List();
        //1、Collectors.toList()
        System.out.println("-------toList--------");
        employee2List.stream().map(Employee2::getName).collect(Collectors.toList()).forEach(System.out::println);
        //2、Collectors.toSet()
        System.out.println("-------toSet--------");
        employee2List.stream().map(Employee2::getName).collect(Collectors.toSet()).forEach(System.out::println);
        //3、Collectors.toCollection(HashSet::new)
        System.out.println("-------toCollection--------");
        employee2List.stream().map(Employee2::getName).collect(Collectors.toCollection(HashSet::new)).forEach(System.out::println);
        //4、Collectors.maxBy(Double::compareTo)
        System.out.println("-------maxBy--------");
        System.out.println(employee2List.stream().map(Employee2::getSalary).collect(Collectors.maxBy(Double::compareTo)).get());
        System.out.println(employee2List.stream().collect(Collectors.maxBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))).get());
        //5、Collectors.minBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
        System.out.println("-------minBy--------");
        System.out.println(employee2List.stream().map(Employee2::getSalary).collect(Collectors.minBy(Double::compareTo)).get());
        System.out.println(employee2List.stream().collect(Collectors.minBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))).get());
        //6、Collectors.summingDouble(Employee2::getSalary)
        System.out.println("-------summingDouble--------");
        System.out.println(employee2List.stream().collect(Collectors.summingDouble(Employee2::getSalary)));
        //7、(Collectors.averagingDouble(Employee2::getSalary)
        System.out.println("-------averagingDouble--------");
        System.out.println(employee2List.stream().collect(Collectors.averagingDouble(Employee2::getSalary)));
        //8、Collectors.counting()
        System.out.println("-------counting--------");
        System.out.println(employee2List.stream().collect(Collectors.counting()));
        //9、Collectors.summarizingDouble(Employee2::getSalary)
        System.out.println("-------summarizingDouble--------");
        System.out.println(employee2List.stream().collect(Collectors.summarizingDouble(Employee2::getSalary)));
        //10、Collectors.groupingBy(Employee2::getStatus)
        System.out.println("-------单级分组--------");
        System.out.println(employee2List.stream().collect(Collectors.groupingBy(Employee2::getStatus)));
        //11、多级分组Collectors.groupingBy()
        System.out.println("-------多级分组--------");
        System.out.println(employee2List.stream().collect(Collectors.groupingBy(Employee2::getStatus, Collectors.groupingBy((e) -> {
            if (e.getAge() >= 60) {
                return "老年";
            } else if (e.getAge() >= 35) {
                return "中年";
            } else {
                return "成年";
            }
        }))));
        //12、Collectors.partitioningBy(),且支持多级分区
        System.out.println("-------partitioningBy--------");
        System.out.println(employee2List.stream().collect(Collectors.partitioningBy((e)->e.getSalary()>5000)));
        //13、Collectors.joining(",")
        System.out.println("-------joining--------");
        System.out.println(employee2List.stream().map(Employee2::getName).collect(Collectors.joining(",")));
        //14、Collectors.joining(",","prefix","suffix")
        System.out.println(employee2List.stream().map(Employee2::getName).collect(Collectors.joining(",","prefix","suffix")));
    }

    /**
     * 测试map和flatMap的区别，类似于集合中的add和addAll方法
     *
     * @param str
     * @return
     */
    private static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (Character character : str.toCharArray()) {
            list.add(character);
        }
        return list.stream();
    }

    /**
     * 创建流及中间操作使用
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

    /**
     * 终止操作使用
     *
     * @return
     */
    private static List<Employee2> createEmployee2List() {
        List<Employee2> employees = Arrays.asList(
                new Employee2(102, "李四", 59, 6666.66, Status.BUSY),
                new Employee2(101, "张三", 18, 9999.99, Status.FREE),
                new Employee2(103, "王五", 28, 3333.33, Status.VOCATION),
                new Employee2(104, "赵六", 8, 7777.77, Status.BUSY),
                new Employee2(104, "赵六", 8, 7777.77, Status.FREE),
                new Employee2(104, "赵六", 8, 7777.77, Status.FREE),
                new Employee2(105, "田七", 38, 5555.55, Status.BUSY)
        );
        return employees;
    }
}
