package lotterymachine.utils;

public enum LotteryRule {
    MINIMUM(1),
    MAXIMUM(45),
    TICKET_SIZE(6),
    TICKET_PRICE(1000);

    private final int number;

    LotteryRule(int number) {
        this.number = number;
    }

    public static boolean checkRange(int number) {
        return number >= MINIMUM.number && number <= MAXIMUM.number;
    }

    public int getNumber() {
        return number;
    }
}
