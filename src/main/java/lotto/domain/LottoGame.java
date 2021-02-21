package lotto.domain;

public class LottoGame {

    public Lottos buyAutoLottos(int autoAmount) {

        return new Lottos(autoAmount);
    }

    public LottoGameResult compareWithWinningLotto(Lottos lottos, WinningLotto winningLotto) {
        LottoGameResult lottoGameResult = new LottoGameResult();

        for (Lotto lotto : lottos.toList()) {
            lottoGameResult.add(winningLotto.findRank(lotto));
        }

        return lottoGameResult;
    }
}
