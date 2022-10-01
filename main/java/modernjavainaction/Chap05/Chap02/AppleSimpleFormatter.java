package modernjavainaction.Chap05.Chap02;

public class AppleSimpleFormatter implements AppleFormatter{

    @Override
    public String accept(Apple apple) {
        return "An apple of " + apple.getWeight() + "g";
    }
}
