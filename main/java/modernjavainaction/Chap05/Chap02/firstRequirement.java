package modernjavainaction.Chap05.Chap02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

/*
    첫 번째 : 녹색 사과 필터 -> 색을 파라미터화
 */
public class firstRequirement {

    //1. 녹색사과 필터링
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if(Color.GREEN.equals(apple.getColor()) ){
                result.add(apple);
            }
        }
        return result;
    }

    //2. 색을 파라미터화
    public static List<Apple> filterAppleByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if(apple.getColor() == color){
                result.add(apple);
            }
        }
        return result;
    }

    //2-2 무게의 기준 필터링
    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if(apple.getWeight() > weight){
                result.add(apple);
            }
        }
        return result;
    }


    // 3. 가능한 모든 속성으로 필터링
    public static List<Apple> filterApples(List<Apple> inventory, Color color, int weight, boolean flag){
        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory) {
            //색이나 무게를 선택하는 방법이 마음에 들지 않는다.
            if((flag && apple.getColor().equals(color)) || (!flag && apple.getWeight() > weight)){
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p){

        List<Apple> result = new ArrayList<>();
        for(Apple apple : inventory){
            if(p.test(apple)){ // Predicate 객체로 사과 검사 조건을 캡슐화했다.
                result.add(apple);
            }
        }
        return result;
    }


    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter p){
        for(Apple apple : inventory){
            String output = p.accept(apple);
            System.out.println(output);
        }
    }

    public static void main(String... args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(80, Color.GREEN, Country.KOREA),
                new Apple(150, Color.GREEN, Country.USA),
                new Apple(120, Color.RED, Country.JAPAN),
                new Apple(150, Color.RED, Country.KOREA));

        System.out.println("---------첫 번째 녹색 사과 필터링으로 녹색사과 추출---------");
        List<Apple> green = filterGreenApples(inventory);
        System.out.println(green);

        System.out.println("---------두 번째 색을 파라미터화 시켜 추출하기---------");
        List<Apple> greenApples = filterAppleByColor(inventory, Color.GREEN);
        System.out.println(greenApples);

        List<Apple> readApples = filterAppleByColor(inventory, Color.RED);
        System.out.println(readApples);


        System.out.println("---------무게의 기준을 넣었을 때---------");
        List<Apple> weightApples = filterApplesByWeight(inventory, 80);
        System.out.println(weightApples);


        System.out.println("---------가능한 모든 속성으로 필터링---------");
        List<Apple> greenApples1 = filterApples(inventory, Color.GREEN, 0, true);
        System.out.println(greenApples1);

        System.out.println("---------Predicate 사용  ---------");
        List<Apple> redAndHeavyApples = filterApples(inventory,new AppleRedAndHeavyPredicate());
        System.out.println(redAndHeavyApples);

        System.out.println("---------과제 ---------");
        prettyPrintApple(inventory, new AppleFancyFormatter());
        prettyPrintApple(inventory, new AppleSimpleFormatter());


        System.out.println("---------역정렬---------");
        inventory.sort(comparing(Apple::getWeight).reversed());
        System.out.println(inventory);


        System.out.println("---------정렬---------");
        inventory.sort(comparing(Apple::getWeight)); // 디폴트로 오름차순으로 되어있다.!
        System.out.println(inventory);



        System.out.println("---------같은 무게가 존재한다면 ? ---------");
        inventory.sort(comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getCountry));
        System.out.println(inventory);


        System.out.println("---------?  ? ---------");

        Comparator<Apple> c = (Apple a1, Apple a2) -> a1.getWeight() - a2.getWeight();
        System.out.println(c);

        List<Apple> greenApples23 = filter(inventory, (Apple a) -> a.getColor() == Color.GREEN);
        System.out.println(greenApples23);


    }

    public static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if(p.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }
}
