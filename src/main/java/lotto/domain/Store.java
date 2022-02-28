package lotto.domain;

import lotto.domain.vo.Money;

import java.util.ArrayList;
import java.util.List;

public class Store {

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
        return leftMoney.isGreaterThan(Money.createMinimumLottoMoney());
    }

    private Lotto buy() {
        leftMoney = leftMoney.minus(Money.createMinimumLottoMoney());
        return LottoGenerator.generate();
    }
}
