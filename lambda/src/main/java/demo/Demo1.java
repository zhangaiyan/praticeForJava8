package demo;

/**
 * @author zhangchaoyue
 * @date 2019/11/5
 */
public class Demo1 {

    public static void main(String[] args) {
        beforeJava8();
        afterJava8();
    }

    private static void beforeJava8() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("before java 8");
            }
        }).start();
    }

    /**
     * lambda表达式替换匿名内部类
     */
    private static void afterJava8() {
        new Thread(() -> {
            System.out.println("after java 8");
        }).start();
    }
}
