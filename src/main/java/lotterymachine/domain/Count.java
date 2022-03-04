package lotterymachine.domain;

public class Count {
    private final int value;

    public Count(Money money) {
        this.value = money.getValue() / LotteryTicket.PER_PRICE;
    }

    public int getValue() {
        return this.value;
    }
}
