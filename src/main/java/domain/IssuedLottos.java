package domain;

import java.util.ArrayList;
import java.util.List;

public class IssuedLottos {
    private List<IssuedLotto> lottos;

    private IssuedLottos(List<IssuedLotto> lottos) {
        this.lottos = lottos;
    }

    public static IssuedLottos of(List<IssuedLotto> lottos) {
        return new IssuedLottos(lottos);
    }

    public static IssuedLottos getTotalLottosOf(IssuedLottos manual, IssuedLottos auto) {
        List<IssuedLotto> lottos = new ArrayList<>();

        lottos.addAll(manual.lottos);
        lottos.addAll(manual.lottos);
        return new IssuedLottos(lottos);
    }

    public int getPurchasedAmount() {
        return lottos.size() * IssuedLotto.PRICE;
    }

    public List<IssuedLotto> getLottos() {
        return lottos;
    }

    public int size() {
        return lottos.size();
    }
}
