package domain;

public class LottoGame {
    private Lottos lottos;
    private WinningNumber winningNumber;

    public LottoGame(Lottos lottos, WinningNumber winningNumber) {
        this.lottos = lottos;
        this.winningNumber = winningNumber;
    }

    public LottoResult calculateResults() {
        return new LottoResult(lottos, winningNumber);
    }

}
