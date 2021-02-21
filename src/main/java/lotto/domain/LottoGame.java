package lotto.domain;

public class LottoGame {

    public Lottos buyAutoLottos(int autoAmount) {

        return new Lottos(autoAmount);
    }

    public LottoGameResult compareWithWinningLotto(Lottos manual, Lottos auto, WinningLotto winningLotto) {
        LottoGameResult lottoGameResult = new LottoGameResult();

        addMatchLotto(manual, winningLotto, lottoGameResult);
        addMatchLotto(auto, winningLotto, lottoGameResult);

        return lottoGameResult;
    }

    private void addMatchLotto(Lottos manual, WinningLotto winningLotto, LottoGameResult lottoGameResult) {
        for (Lotto manualLotto : manual.toList()) {
            lottoGameResult.add(winningLotto.findRank(manualLotto));
        }
    }
}
