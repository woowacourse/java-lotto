package lotto.domain.number;

public class ManualCount {

    private static final int COUNT_MIN = 0;

    private final int count;

    private ManualCount(int count) {
        this.count = count;
    }

    public static ManualCount valueOf(String count) {
        int convertedCount = validateNumeric(count);
        validateRange(convertedCount);

        return new ManualCount(convertedCount);
    }

    private static int validateNumeric(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력이 숫자가 아니거나 Integer 범위를 벗어났습니다.");
        }
    }

    private static void validateRange(int input) {
        if (input < COUNT_MIN) {
            throw new IllegalArgumentException("불가능한 로또 개수 입니다.");
        }
    }

    public int unwrap() {
        return count;
    }
}
