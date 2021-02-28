package lotto.domain;

public class LottoGame {

    private Lottos manualLottos;
    private Lottos autoLottos;

    public void buyManualLottos(Lottos manualLottos) {
        this.manualLottos = manualLottos;
    }

    public void buyAutoLottos(int autoAmount) {
        this.autoLottos = new Lottos(autoAmount);
    }

    public LottoGameResult draw(WinningLotto winningLotto) {
        final LottoGameResult lottoGameResult = new LottoGameResult();

        lottoGameResult.add(manualLottos.findMatchLotto(winningLotto));
        lottoGameResult.add(autoLottos.findMatchLotto(winningLotto));

        return lottoGameResult;
    }

    public Lottos toManualLottos() {
        return manualLottos;
    }

    public Lottos toAutoLottos() {
        return autoLottos;
    }
}
