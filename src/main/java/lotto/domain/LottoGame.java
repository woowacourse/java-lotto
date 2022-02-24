package lotto.domain;

import java.util.List;

public class LottoGame {

    private final Lottos lottos = new Lottos();

    public void purchase(Money money) {
        lottos.purchase(money);
    }

    public LottoResults confirmWinnings(WinningNumbers winningNumbers) {
        return new LottoResults(lottos.confirmWinnings(winningNumbers));
    }

    public List<Lotto> getLottos() {
        return lottos.getLottos();
    }
}
