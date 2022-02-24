package lotto.constants;

public enum NumberLimit {

    MINIMUM(1),
    MAXIMUM(45);

    private int limit;

    NumberLimit(int limit) {
        this.limit = limit;
    }

    public static boolean checkOutOfRange(int number) {
        return number < MINIMUM.limit || number > MAXIMUM.limit;
    }

    public int getLimit() {
        return this.limit;
    }
}
