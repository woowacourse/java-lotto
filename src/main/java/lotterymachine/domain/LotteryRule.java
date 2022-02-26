package lotterymachine.domain;

public enum LotteryRule {
    MINIMUM(1),
    MAXIMUM(45),
    TICKET_SIZE(6);

    private final int number;

    LotteryRule(int number) {
        this.number = number;
    }

    public static boolean isRangeOfNumber(int number) {
        return number >= MINIMUM.number && number <= MAXIMUM.number;
    }

    public static boolean isLotteryTicketSize(int size) {
        return size == TICKET_SIZE.number;
    }
}
