package domain;

import domain.strategy.LottoNumberStrategy;

import java.util.ArrayList;
import java.util.List;

public class Lottos {
    private final List<LottoNumbers> lottos;

    public Lottos(LottoNumberStrategy lottoNumberStrategy, PurchaseCount count) {
        this.lottos = createLottos(lottoNumberStrategy, count);
    }

    private Lottos(List<LottoNumbers> lottos) {
        this.lottos = lottos;
    }

    public Lottos concatenate(Lottos lottos) {
        List<LottoNumbers> copiedLottos = new ArrayList<>(this.lottos);
        copiedLottos.addAll(lottos.getLottos());

        return new Lottos(copiedLottos);
    }

    private List<LottoNumbers> createLottos(LottoNumberStrategy lottoNumberStrategy, PurchaseCount count) {
        return lottoNumberStrategy.generate(count);
    }

    public List<LottoNumbers> getLottos() {
        return lottos;
    }
}
