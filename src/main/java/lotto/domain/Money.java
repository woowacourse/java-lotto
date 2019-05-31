package lotto.domain;

import lotto.Exception.IllegalPriceException;

import java.util.Map;

public class Money {
    private static final int MONEY_OFFSET = 1000;
    private static final int PERSENT = 100;
    private final int money;

    public Money(int money) {
        if (money % MONEY_OFFSET != 0 || money < 0) {
            throw new IllegalPriceException();
        }

        this.money = money;
    }

    public int getSize(){
        return this.money / MONEY_OFFSET;
    }

    public int getRate(Map<Rank, Integer> map) {
        return map.keySet().stream()
                .mapToInt(r -> r.getMoney() * map.get(r))
                .sum()
                / money * PERSENT;
    }
}
