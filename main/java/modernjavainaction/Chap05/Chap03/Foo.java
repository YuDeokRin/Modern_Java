package modernjavainaction.Chap05.Chap03;

import java.util.function.Predicate;

public class Foo {
    public static void main(String[] args) {
        IntPredicate  evenNumbers = (int i) -> i % 2 ==0;
        System.out.println(evenNumbers.test(1000));

        Predicate<Integer> oddNumbers = (Integer i) -> i % 2 != 0;
        System.out.println(oddNumbers.test(1000));


//        LongPredicate longPredicate =
    }
}
