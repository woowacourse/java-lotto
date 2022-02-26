package domain;

import java.util.Arrays;

public enum RankPrice {

    FIRST(6, 2000000000, false),
    SECOND(5, 30000000, true),
    THIRD(5, 1500000, false),
    FOURTH(4, 50000, false),
    FIFTH(3, 5000, false);

    private static final String ERROR_INVALID_WIN_COUNT_MESSAGE = "일치하는 값이 없습니다.";

    private final int correctNumber;
    private final int winPrice;
    private final boolean bonus;

    RankPrice(final int correctNumber, final int winPrice, final boolean bonus) {
        this.correctNumber = correctNumber;
        this.winPrice = winPrice;
        this.bonus = bonus;
    }

    public static RankPrice findByCount(final int correctNumber, final boolean bonus) {
        return Arrays.stream(RankPrice.values())
                .filter(winPrice -> winPrice.correctNumber == correctNumber)
                .filter(winPrice -> winPrice.bonus == bonus)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(ERROR_INVALID_WIN_COUNT_MESSAGE));
    }

    public int getCount() {
        return correctNumber;
    }

    public int getPrice() {
        return winPrice;
    }
}
