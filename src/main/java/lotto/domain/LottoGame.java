package lotto.domain;

import lotto.utils.LottoGenerator;
import lotto.utils.RandomGenerator;

public class LottoGame {

    public Lottos buyLottos(Money money) {
        LottoGenerator lottoGenerator = new RandomGenerator();

        return new Lottos(lottoGenerator, money);
    }

    public LottoGameResult compareWithWinningLotto(Lottos lottos, WinningLotto winningLotto) {
        LottoGameResult lottoGameResult = new LottoGameResult();
        for (Lotto lotto : lottos.toList()) {
            lottoGameResult.add(winningLotto.findRank(lotto));
        }
        return lottoGameResult;
    }
}
