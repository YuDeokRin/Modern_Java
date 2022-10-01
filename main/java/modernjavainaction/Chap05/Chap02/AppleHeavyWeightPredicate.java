package modernjavainaction.Chap05.Chap02;

//무거운 사과만 선택
public class AppleHeavyWeightPredicate implements ApplePredicate{

    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}
