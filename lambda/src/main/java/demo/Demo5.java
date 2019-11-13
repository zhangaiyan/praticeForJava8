package demo;

import java.util.Optional;

/**
 * @author zhangchaoyue
 * @date 2019/11/5
 */
public class Demo5 {

    public static void main(String[] args) {

        Optional<String> optional = Optional.ofNullable(null);
        System.out.println(optional.isPresent());
        System.out.println(optional.get());
        System.out.println(optional.orElse("456"));
        optional.ifPresent((s)->System.out.println(s));

    }

}
