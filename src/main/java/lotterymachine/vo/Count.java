package lotterymachine.vo;

public class Count {
    private int number;

    private Count(int number) {
        this.number = number;
    }

    public static Count from(int number) {
        return new Count(number);
    }

    public int getNumber() {
        return number;
    }

    public Count increase() {
        this.number++;
        return this;
    }
}
