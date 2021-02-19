package lotto.domain;

import lotto.util.LottoGenerator;

import java.util.ArrayList;
import java.util.List;

public class Money {
    private static final int LOTTO_PRICE = 1000;

    private final int money;

    public Money(String money) {
        validateNumber(money);
        this.money = Integer.parseInt(money);
    }

    public List<Lotto> buyLotto() {
        int count = countBuyLotto();
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto(LottoGenerator.make()));
        }
        return lottos;
    }

    public int countBuyLotto() {
        return money / LOTTO_PRICE;
    }

    public float calculateProfitRate(float profit) {
        return profit / money;
    }

    private void validateNumber(String money) {
        if (!money.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("금액은 정수만 입력 가능합니다");
        }
        if (Integer.parseInt(money) < 1000) {
            throw new IllegalArgumentException("1000원 이상의 금액만 입력 가능합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money)) return false;

        Money money1 = (Money) o;

        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return money;
    }
}
