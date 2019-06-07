package domain;

import java.util.List;

public class IssuedLottos {
    private List<IssuedLotto> lottos;

    private IssuedLottos(List<IssuedLotto> lottos) {
        this.lottos = lottos;
    }

    public static IssuedLottos of(List<IssuedLotto> issuedLottos) {
        return new IssuedLottos(issuedLottos);
    }

    public int getPurchasedAmount() {
        return lottos.size() * IssuedLotto.PRICE;
    }

    public void addAll(IssuedLottos issuedLottos) {
        lottos.addAll(issuedLottos.lottos);
    }

    public List<IssuedLotto> getLottos() {
        return lottos;
    }
}
