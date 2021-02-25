package domain.lottoGame;

public class Tickets {
    private final int count;

    public Tickets(int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Tickets can't be negative");
        }
        this.count = count;
    }

    public Tickets use(int value) {
        return new Tickets(count - value);
    }

    public int left() {
        return count;
    }
}
