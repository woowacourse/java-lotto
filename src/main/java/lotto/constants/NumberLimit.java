package lotto.constants;

public enum NumberLimit {
    MINIMUM(1),
    MAXIMUM(45);

    private int limit;

    NumberLimit(int limit) {
        this.limit = limit;
    }

    public static void validateInRange(int number) {
        if (number < MINIMUM.getLimit() || number > MAXIMUM.getLimit()) {
            throw new IllegalArgumentException("로또 번호는 1이상 45이하이어야 한다.");
        }
    }

    public int getLimit() {
        return this.limit;
    }
}
