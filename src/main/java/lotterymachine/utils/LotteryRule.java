package lotterymachine.utils;

public enum LotteryRule {
    MINIMUM_NUMBER(1),
    MAXIMUM_NUMBER(46),
    TICKET_SIZE(6);

    private final int number;

    LotteryRule(int number) {
        this.number = number;
    }

    public static boolean checkRange(int number) {
        return number < MINIMUM_NUMBER.number || number > MAXIMUM_NUMBER.number;
    }

    public int getNumber() {
        return number;
    }
}
