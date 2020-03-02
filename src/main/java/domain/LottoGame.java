package domain;

public class LottoGame {
    private Lottos lottos;
    private WinningNumber winningNumber;

    public LottoGame(Lottos lottos, WinningNumber winningNumber) {
        this.lottos = lottos;
        this.winningNumber = winningNumber;
    }

    public void calculateResults(final LottoResult lottoResult) {
        lottoResult.countWinningLotto(lottos, winningNumber);
    }

}
