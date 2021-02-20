package lotto.domain;

public class LottoGame {

    public Lottos buyLottos(Money money) {

        return new Lottos(money);
    }

    public LottoGameResult compareWithWinningLotto(Lottos lottos, WinningLotto winningLotto) {
        LottoGameResult lottoGameResult = new LottoGameResult();

        for (Lotto lotto : lottos.toList()) {
            lottoGameResult.add(winningLotto.findRank(lotto));
        }

        return lottoGameResult;
    }
}
