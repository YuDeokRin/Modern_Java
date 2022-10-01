package modernjavainaction.Chap05.Chap05;

import modernjavainaction.Chap05.Chap04.Dish;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;
import static modernjavainaction.Chap05.Chap04.Dish.menu;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        List<String> dishNames = menu.stream()
                                     .map(Dish::getName)
                                     .collect(toList());

        System.out.println(dishNames);




        List<Integer> dishNameLengths=  menu.stream()
                                            .map(Dish::getName)
                                            .map(String::length)
                                            .collect(toList());

        System.out.println(dishNameLengths);

        List<Integer> number = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squareRoot = number.stream().map(n -> n * n).collect(toList());
        System.out.println(squareRoot);


        List<Integer> num1 = Arrays.asList(1, 2, 3);
        List<Integer> num2 = Arrays.asList(3, 4);

        List<int[]> pairs = num1.stream()
                                .flatMap(i -> num2.stream()
                                        .map(j -> new int[]{i, j}))
                                .collect(toList());

        pairs.forEach(pair -> System.out.printf("(%d, %d)", pair[0], pair[1]));

        System.out.println();

        List<int[]> pairs2 = num1.stream()
                .flatMap(i -> num2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j}))
                        .collect(toList());

        pairs2.forEach(pair2 -> System.out.printf("(%d, %d)", pair2[0], pair2[1]));


        System.out.println("------필터링 문제---------");

        List<Dish> dishs = menu.stream()
                .filter(d -> d.getType() == Dish.Type.MEAT)
                .limit(2)
                .collect(toList());


        System.out.println(dishs);


        System.out.println("------map()로 전달해서 스트림의 요리명을 추출하기---------");

        List<String> dishName = menu.stream()
                .map(Dish::getName)
                .collect(toList());

        System.out.println(dishName);

        System.out.println(dishName.getClass().getName());


        System.out.println("------각 단어의 글자 수의 리스트를 반환--------");
        List<String> words = Arrays.asList("Modern", "Java", "In", "Action");
        List<Integer> wordlengths = words.stream()
                .map(String::length)
                .collect(toList());

        System.out.println(wordlengths);


        System.out.println("------각 요리명의 길이 구하기-------");
        List<Integer> dishlengths = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(toList());

        System.out.println(dishlengths);


        System.out.println("------고유 문자로 이루어진 리스트를 반환해보자 -------");
        System.out.println("------ hello world ->  따로 찍기  ");
        List<String> helloworld = Arrays.asList("Hello", "World");
        List<String> helloworlds = helloworld.stream()
                .map(word -> word.split("")) // 각 단어를 개별 문자열 배열로 변환
                .flatMap(Arrays::stream) // 각 배열의 별도의 스트림으로 생성
                .distinct()
                .collect(toList());


        System.out.println(helloworlds);


        System.out.println("------------매핑 문제 -----------");
        List<Integer> Number = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares = Number.stream()
                .map(n -> n * n)
                .collect(toList());

        System.out.println(squares);

        System.out.println("------------anyMatch-----------");
        if(menu.stream().anyMatch(Dish::isVegetarian)){
            System.out.println("The menu is (somewhat) vegetarian friendly !! ");
        }

        System.out.println("---------allMatch-----------");

        boolean isHealthy = menu.stream().allMatch(dish -> dish.getCalories() < 1000);
        System.out.println(isHealthy);


        System.out.println("---------noneMatch-----------");
        boolean isHealthy1 = menu.stream().noneMatch(d -> d.getCalories() >= 1000);
        System.out.println(isHealthy1);


        System.out.println("---------findAny()--------");
        Optional<Dish> dish = menu.stream().filter(Dish::isVegetarian).findAny();

        System.out.println(dish);



        //reduce
        System.out.println("-----------합 구하기 ------");
        List<Integer> Number2 = Arrays.asList(4, 5, 3, 9);
        int sum = Number2.stream().reduce(0, Integer::sum);
        System.out.println(sum);


        System.out.println("-----------최댓값 구하기 ------");
        Optional<Integer> max = Number2.stream().reduce(Integer::max);
        System.out.println(max);



        System.out.println("-----------최댓값 구하기 ------");
        Optional<Integer> min = Number2.stream().reduce(Integer::min);
        System.out.println(min);


        //퀴즈 p.174
        System.out.println("-------map과 reduce메서드를 이용해서 스트림의 요리 개수를 계산하시오.");
        //스트림의 각 요소를 1로 매핑한 다음에 reduce로 이들의 합계를 계산하는 방식으로 문제를 해결할 수 있다.
        // 즉, 스트림에 저장된 숫자를 차례로 더한다.
        int count = menu.stream().map(d -> 1).reduce(0, (a, b) -> a + b);
        System.out.println(count);


        System.out.println("------count로 요소세기----");
        long count2 = menu.stream().count();
        System.out.println(count2);


        /*실전 연습
            트랜잭션(거래)을 실행하는 거래자 예제를 살펴보자
            1. 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리하시오.
            2. 거래자가 근무하는 모든 도시를 중복 없이 나열하시오.
            3. 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오.
            4. 모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오.
            5. 밀라노에 거래자가 었는가 ?
            6. 케임브리지에 거주하는 거래자의 모든 트랜잭션값을 출력하시오.
            7. 전체 트랜잭션 중 최댓값은 얼마인가?
            8. 전체 트랜젹션 중 최솟값은 얼마인가 ?
         */

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );


        System.out.println("----1. 2011년에 일어난 모든 트랜잭션을 찾아 값을 오름차순으로 정리하시오.----");
        List<Transaction> tr2011 = transactions.stream()
                                                     .filter(transaction -> transaction.getYear() == 2011)// 2011년에 발생한 트랜잭션을 필터링하도록 프레디케이트를 넘겨준다.
                                                     .sorted(comparing(Transaction::getValue)) // 트랜잭션 value로 요소 정렬
                                                     .collect(toList()); // 결과 스트림의 모든 요소를 리스트로 변환

        System.out.println(tr2011);


        System.out.println("----2. 거래자가 근무하는 모든 도시를 중복 없이 나열하시오.----");
        List<String> cities = transactions.stream()
                                                .map(transaction -> transaction.getTrader().getCity()) // 트랜잭션과 관련한 각 거래자의 도시 추출
                                                .distinct() // 고유 도시만 선택
                                                .collect(toList()); // 결과 스트림의 모든 요소를 리스트로 반환

        System.out.println(cities);


        System.out.println("----3. 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬하시오.----");
        List<Trader> traders = transactions.stream()
                                                .map(Transaction::getTrader)
                                                .filter(trader -> trader.getCity().equals("Cambridge"))
                                                .distinct()
                                                .sorted(comparing(Trader::getName)) // 결과 스트림의 거래자를 이름으로 정렬
                                                .collect(toList());


        System.out.println(traders);


        System.out.println("----4. 모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오.----");
        String traderStr = transactions.stream()
                                                 .map(transaction -> transaction.getTrader().getName())
                                                 .distinct()
                                                 .sorted()
                                                 .reduce("", (n1, n2) -> n1 + n2);
        System.out.println(traderStr);

        System.out.println("방법.2");
        System.out.println("----4. 모든 거래자의 이름을 알파벳순으로 정렬해서 반환하시오.--- ");
        String traderStr2 = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(joining());

        System.out.println(traderStr2);


        System.out.println("----5. 밀라노에 거래자가 있는가 ?----");
        boolean milanBased = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));

        System.out.println(milanBased);


        System.out.println("----6. 케임브리지에 거주하는 거래자의 모든 트랜잭션값을 출력하시오.----");
         transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue) // 각 값을 출력
                .forEach(System.out::println); // 이 거래자들의 값 추출



        System.out.println("----7. 전체 트랜잭션 중 최댓값은 얼마인가?----");
        Optional<Integer> highestValue = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        System.out.println(highestValue);


        System.out.println("----8. 전체 트랜젹션 중 최솟값은 얼마인가 ?----");
        Optional<Integer> smallestTransaction = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min);
        System.out.println(smallestTransaction);

        System.out.println("방법. 2");
        System.out.println("----8. 전체 트랜잭션 중 최솟값은 얼마인가 ? ----");
        Optional<Transaction> smallestTransaction2 = transactions.stream()
                        .reduce((t1, t2) -> t1.getValue() < t2.getValue() ? t1 : t2); // 각 트랜잭션값을 반복 비교해서 가장 작은 트랜잭션 검색


        System.out.println(smallestTransaction2);

        System.out.println("----스트림 최대값이나 최솟값을 계산하는데 사용할 키를 지정하는 Comparator를 인수로 받은 min과 max 메소드를 제공한다");
        Optional<Transaction> smallTransaction = transactions.stream()
                .min(comparing(Transaction::getValue));

        System.out.println(smallTransaction);


        System.out.println("\n------------ 숫자형 스트림--------------");
        System.out.println("--------------메뉴의 칼로리의 합계--------------");
        int calories = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);

        System.out.println(calories);


        System.out.println("-----------1부터 100까지에서 짝수 구하기 -------");
        IntStream evenNumbers = IntStream.rangeClosed(1, 100)
                .filter(n -> n % 2 == 0);

        System.out.println(evenNumbers.count()); //50개

        

    }
}
