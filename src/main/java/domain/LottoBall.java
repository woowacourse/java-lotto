package domain;

public class LottoBall {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int ARRAY_EXTRA_INDEX = 1;

    private static final LottoBall[] lottoBalls = new LottoBall[MAX_NUMBER + ARRAY_EXTRA_INDEX];

    private final int number;

    private LottoBall(final int number) {
        this.number = number;
    }

    static {
        for (int i = MIN_NUMBER; i <= MAX_NUMBER; i++) {
            lottoBalls[i] = new LottoBall(i);
        }
    }

    public static LottoBall valueOf(final int number) {
        validateLottoBall(number);
        return lottoBalls[number];
    }

    private static void validateLottoBall(final int number) {
        if (number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new IllegalArgumentException(MIN_NUMBER + "~" + MAX_NUMBER + " 사이의 번호만 허용합니다.");
        }
    }

    public int getNumber() {
        return number;
    }
}
