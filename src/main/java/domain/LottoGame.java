package domain;

public class LottoGame {
    private final LottoMachine lottoMachine;

    public LottoGame(LottoMachine lottoMachine) {
        this.lottoMachine = lottoMachine;
    }

    public Lottos createAutoLottos() {
        return lottoMachine.createAutoLottos();
    }

    public LottoResult createLottoResult(Lottos lottos, WinningLotto winningLotto) {
        return new LottoResult(lottos.calculateRank(winningLotto));
    }
}
