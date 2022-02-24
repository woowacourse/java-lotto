package lotterymachine.dto;

public class Count {
    private int number;

    public Count(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public Count increase() {
        this.number++;
        return this;
    }
}
