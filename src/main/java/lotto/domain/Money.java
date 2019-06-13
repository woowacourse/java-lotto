package lotto.domain;

import lotto.Exception.IllegalPriceException;
import lotto.Exception.InvalidPurchaseException;

import java.util.Map;

public class Money {
    private static final int MONEY_OFFSET = 1000;
    private static final int PERSENT = 100;
    private static final int MIN_MONEY = 0;
    private final int money;

    public Money(int money) {
        if (money % MONEY_OFFSET != MIN_MONEY || money < MIN_MONEY) {
            throw new InvalidPurchaseException("올바른 구입금액을 입력해주세요");
        }
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public int getSize() {
        return this.money / MONEY_OFFSET;
    }

    public double getRate(Map<Rank, Integer> map) {
        double rate = map.keySet()
                .stream()
                .mapToDouble(r -> r.getMoney() * map.get(r))
                .sum()
                / (double) money * PERSENT;
        return Math.round(rate * 100) / 100.0; //셋째자리에서 반올림
    }

    public boolean isOverPrice(int customLottoCount) {
        return this.getSize() < customLottoCount;
    }

    public int getAutoLottoCount(String[] customLottoNumbersInput) {
        return this.getSize() - customLottoNumbersInput.length;
    }
}
