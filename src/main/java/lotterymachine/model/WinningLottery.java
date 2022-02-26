package lotterymachine.model;

import java.util.Arrays;

public enum WinningLottery {
    ZERO(0, 0, false),
    THREE(3, 5000, false),
    FOUR(4, 50000, false),
    FIVE(5, 150000, false),
    BONUS_FIVE(5, 30000000, true),
    SIX(6, 2000000000, false);

    private static final int INITIAL_NUMBER_OF_MATCHING_TICKET = 0;

    private final int number;
    private final int price;
    private final boolean bonus;

    WinningLottery(int number, int price, boolean bonus) {
        this.number = number;
        this.price = price;
        this.bonus = bonus;
    }

    public static WinningLottery find(int number, boolean bonus) {
        return Arrays.stream(values())
                .filter(value -> value.matchWinningLottery(number, bonus))
                .findFirst()
                .orElse(WinningLottery.ZERO);
    }

    private boolean matchWinningLottery(int number, boolean bonus) {
        return this.number == number && this.bonus == bonus;
    }

    public int getNumber() {
        return number;
    }

    public int getPrice() {
        return price;
    }
}
