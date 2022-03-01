package lotterymachine.domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public enum WinningLotteryRank {
    ZERO(0, 0, false),
    THREE(3, 5_000, false),
    FOUR(4, 50_000, false),
    FIVE(5, 150_000, false),
    BONUS_FIVE(5, 30_000_000, true),
    SIX(6, 2_000_000_000, false);

    private static final int INITIAL_NUMBER_OF_MATCHING_TICKET = 0;

    private final int number;
    private final int price;
    private final boolean bonus;

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

    public static Map<WinningLotteryRank, Integer> getWinningLotteries() {
        Map<WinningLotteryRank, Integer> winningLotteries = new EnumMap<>(WinningLotteryRank.class);
        Arrays.stream(values())
                .forEach(value -> winningLotteries.put(value, INITIAL_NUMBER_OF_MATCHING_TICKET));
        return winningLotteries;
    }
}
