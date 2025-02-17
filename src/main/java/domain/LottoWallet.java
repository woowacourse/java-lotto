package domain;

import java.util.List;

public class LottoWallet {
    private final List<Lotto> lottoWallet;

    public LottoWallet(List<Lotto> lottos) {
        this.lottoWallet = lottos;
    }

    public List<Lotto> getLottoWallet() {
        return lottoWallet.stream()
                .toList();
    }
}
