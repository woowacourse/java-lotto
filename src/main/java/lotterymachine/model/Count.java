package lotterymachine.model;

public class Count {
    private int number;

    public Count(int number) {
        this.number = number;
    }

    public static Count calculateNumberOfTickets(int amount) {
        return new Count(amount / 1000);
    }

    public int getNumber() {
        return number;
    }

    public Count increase() {
        this.number++;
        return this;
    }
}
