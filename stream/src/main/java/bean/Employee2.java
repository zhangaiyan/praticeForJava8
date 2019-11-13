package bean;

import java.util.Objects;

/**
 * @author zhangchaoyue
 * @date 2019/11/6
 */
public class Employee2 {

    private Integer id;
    private String name;
    private Integer age;
    private double salary;
    private Status status;

    public Employee2() {
    }

    public Employee2(String name) {
        this.name = name;
    }

    public Employee2(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Employee2(Integer id, String name, Integer age, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public Employee2(Integer id, String name, Integer age, double salary, Status status) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee2 employee2 = (Employee2) o;
        return Double.compare(employee2.salary, salary) == 0 &&
                Objects.equals(id, employee2.id) &&
                Objects.equals(name, employee2.name) &&
                Objects.equals(age, employee2.age) &&
                status == employee2.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, salary, status);
    }

    public enum Status{
        FREE,BUSY,VOCATION;
    }
}


