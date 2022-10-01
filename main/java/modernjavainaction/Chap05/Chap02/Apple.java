package modernjavainaction.Chap05.Chap02;

public class Apple {
    private int weight = 0;
    private Color color;

    private Country country;

    public Apple(int weight, Color color, Country country) {
        this.weight = weight;
        this.color = color;
        this.country = country;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int wegiht) {
        this.weight = weight;
    }

    public Color getColor() {
        return color;
    }

    public Country getCountry(){
        return country;
    }

    public void setCountry(Country country) { this.country = country;}

    public void setColor(Color color) {
        this.color = color;
    }

    @SuppressWarnings("boxing")
    @Override
    public String toString(){
        return String.format("Apple{color=%s, weight=%d, country=%s}", color, weight,country);
    }

}
