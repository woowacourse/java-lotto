package domain;

import domain.exception.ExceptionMessage;
import domain.generator.Generator;
import java.util.EnumMap;

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

    public Lottos buyLottos(Generator generator) {
        LottosFactory lottosFactory = new LottosFactory();
        return lottosFactory.from(money / LOTTO_PRICE, generator);
    }

    public double calculateProfit(final EnumMap<Rank, Integer> countRank) {
        long sum = countRank.entrySet().stream()
                .mapToLong(entry -> entry.getKey().calculateTotalPrize(entry.getValue()))
                .sum();

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
