package modernjavainaction.Chap05.Chap02;


//녹색 사과만 선택
public class AppleGreenColorPredicate implements ApplePredicate{
    @Override
    public boolean test(Apple apple) {
        return Color.GREEN.equals(apple.getColor());
    }
}
