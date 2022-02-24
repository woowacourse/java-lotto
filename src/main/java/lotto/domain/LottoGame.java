package lotto.domain;

import java.util.Collections;
import java.util.List;
import lotto.domain.vo.Lotto;
import lotto.domain.vo.Lottos;
import lotto.domain.vo.Money;
import lotto.domain.vo.WinningNumbers;

public class LottoGame {

    private final Lottos lottos = new Lottos();

    public void purchase(Money money) {
        lottos.purchase(money);
    }

    public List<Lotto> getLottos() {
        return Collections.unmodifiableList(lottos.getLottos());
    }

    public LottoResults confirmWinnings(WinningNumbers winningNumbers) {
        return new LottoResults(lottos.confirmWinnings(winningNumbers));
    }
}
