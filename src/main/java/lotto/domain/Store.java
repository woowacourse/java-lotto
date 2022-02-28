package lotto.domain;

import lotto.domain.vo.Money;

import java.util.ArrayList;
import java.util.List;

public class Store {

    public static final int LOTTO_PRICE = 1_000;

    private Money leftMoney;

    public Store(Money leftMoney) {
        this.leftMoney = leftMoney;
    }

    public List<Lotto> buyAutomaticLottos() {
        List<Lotto> lottos = new ArrayList<>();
        while (canBuy()) {
            lottos.add(buy());
        }
        return lottos;
    }

    private boolean canBuy() {
        return leftMoney.isGreaterThan(Money.createMoney(LOTTO_PRICE));
    }

    private Lotto buy() {
        leftMoney = leftMoney.minus(Money.createMoney(LOTTO_PRICE));
        return LottoGenerator.generate();
    }
}
