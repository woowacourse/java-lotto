package lotto.domain;

public class Count {

    private static final String NUMBER_EXCEED_ERROR = "[ERROR] 구매 가능한 수를 초과했습니다.";
    private final int count;

    public Count(int input) {
        validateNegative(input);
        this.count = input;
    }

    public int getCount() {
        return this.count;
    }

    private void validateNegative(int input) {
        if (input < 0) {
            throw new IllegalArgumentException(NUMBER_EXCEED_ERROR);
        }
    }
}
