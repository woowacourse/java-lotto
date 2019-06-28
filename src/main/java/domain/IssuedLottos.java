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

    public static IssuedLottos join(IssuedLottos manual, IssuedLottos auto) {
        List<Lotto> lottos = new ArrayList<>();

        lottos.addAll(manual.lottos);
        lottos.addAll(auto.lottos);
        return new IssuedLottos(lottos);
    }

    public Money getPurchasedAmount() {
        return Lotto.PRICE.multiply(lottos.size());
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    public int size() {
        return lottos.size();
    }

    public void addAll(IssuedLottos lottos) {
        this.lottos.addAll(lottos.lottos);
    }

    public void add(Lotto manualIssueLotto) {
        lottos.add(manualIssueLotto);
    }
}

