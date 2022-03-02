package lotterymachine.domain;

import java.util.Arrays;

public enum WinningLotteryRank {
    ZERO(0, 0),
    THREE(3, 5_000),
    FOUR(4, 50_000),
    FIVE(5, 150_000),
    BONUS_FIVE(5, 30_000_000, true),
    SIX(6, 2_000_000_000);

    private final int number;
    private final int price;
    private final boolean bonus;

    WinningLotteryRank(int number, int price) {
        this.number = number;
        this.price = price;
        this.bonus = false;
    }

    WinningLotteryRank(int number, int price, boolean bonus) {
        this.number = number;
        this.price = price;
        this.bonus = bonus;
    }

    public static WinningLotteryRank find(int number, boolean bonus) {
        return Arrays.stream(values())
                .filter(value -> value.matchWinningLottery(number, bonus))
                .findFirst()
                .orElse(WinningLotteryRank.ZERO);
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
