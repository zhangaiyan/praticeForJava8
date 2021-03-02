package bean;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author zhangchaoyue
 * @date 2020/9/17
 */
public class Person<K,E,L,V> {

    public static void main(String[] args) {
        Person[] people = (Person[]) Array.newInstance(Person.class, 10);
        HashMap hashMap = new HashMap();
        Hashtable hashtable = new Hashtable();
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        AtomicStampedReference atomicStampedReference = new AtomicStampedReference(people,1);0
        AtomicReference atomicReference = new AtomicReference(people);
        AtomicInteger atomicInteger = new AtomicInteger();
    }


}
