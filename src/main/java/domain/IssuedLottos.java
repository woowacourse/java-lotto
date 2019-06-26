package domain;

import domain.money.Money;

import java.util.ArrayList;
import java.util.List;

public class IssuedLottos {
    private List<Lotto> lottos;

    private IssuedLottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static IssuedLottos of(List<Lotto> lottos) {
        return new IssuedLottos(lottos);
    }

    public static IssuedLottos getTotalLottosOf(IssuedLottos manual, IssuedLottos auto) {
        List<Lotto> lottos = new ArrayList<>();

        lottos.addAll(manual.lottos);
        lottos.addAll(auto.lottos);
        return new IssuedLottos(lottos);
    }

    public Money getPurchasedAmount() {
        return Money.amountOf(lottos.size() * Lotto.PRICE);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public int size() {
        return lottos.size();
    }
}
