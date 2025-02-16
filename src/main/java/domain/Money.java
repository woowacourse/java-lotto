package domain;

import global.exception.ExceptionMessage;
import global.generator.RandomGenerator;
import java.util.EnumMap;
import java.util.Map.Entry;

public class Money {
    private static final int LOTTO_PRICE = 1000;

    private final int money;

    public Money(final int money) {
        validateIsPositive(money);
        validatePerPrice(money);
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public Lottos buyLottos() {
        LottosFactory lottosFactory = new LottosFactory(new RandomGenerator());
        return lottosFactory.from(money / LOTTO_PRICE);
    }

    public double calculateProfit(final EnumMap<Rank, Integer> countRank) {
        long sum = 0L;
        for (Entry<Rank, Integer> rankIntegerEntry : countRank.entrySet()) {
            sum += Rank.calculateTotalPrize(rankIntegerEntry.getKey(), rankIntegerEntry.getValue());
        }

        return Math.floor((double) sum / money * 100) / 100;
    }

    private void validateIsPositive(final int money) {
        if (money <= 0) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_POSITIVE.getMessage());
        }
    }

    private void validatePerPrice(final int money) {
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_UNIT_PRICE.getMessage());
        }
    }

}
