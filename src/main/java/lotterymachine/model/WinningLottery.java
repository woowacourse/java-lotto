package lotterymachine.model;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import lotterymachine.vo.Count;

public enum WinningLottery {
    INVALID(0, 0),
    THREE(3, 5_000),
    FOUR(4, 50_000),
    FIVE(5, 1_500_000),
    BONUS_FIVE(5, 30_000_000),
    SIX(6, 2_000_000_000);

    private static final int INITIAL_NUMBER_OF_MATCHING_TICKET = 0;
    private final int number;
    private final int price;

    WinningLottery(int number, int price) {
        this.number = number;
        this.price = price;
    }

    public static Map<WinningLottery, Count> getWinningLotteries() {
        Map<WinningLottery, Count> winningLotteries = new EnumMap<>(WinningLottery.class);
        for (WinningLottery winningLottery: values()) {
            winningLotteries.put(winningLottery, Count.from(INITIAL_NUMBER_OF_MATCHING_TICKET));
        }
        return winningLotteries;
    }

    public static WinningLottery find(boolean bonus, int number) {
        if (number == BONUS_FIVE.number && bonus) {
            return BONUS_FIVE;
        }
        return Arrays.stream(values())
                .filter(value -> value.matchNumber(number))
                .findFirst()
                .orElse(INVALID);
    }

    public boolean isBonus() {
        return this.equals(BONUS_FIVE);
    }

    private boolean matchNumber(int number) {
        return this.number == number;
    }

    public int getNumber() {
        return number;
    }

    public int getPrice() {
        return price;
    }
}
