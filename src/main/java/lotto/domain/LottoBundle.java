package lotto.domain;

import java.util.List;

public class LottoBundle {

    private final List<Lotto> lottoBundle;

    public LottoBundle(List<Lotto> lottoBundle) {
        this.lottoBundle = lottoBundle;
    }

    public int getLottoQuantity() {
        return lottoBundle.size();
    }

    public List<Lotto> getLottoBundle() {
        return lottoBundle;
    }
}
