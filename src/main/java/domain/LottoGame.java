package domain;

import domain.strategy.LottoNumberGenerateStrategy;

public class LottoGame {
    private final LottoQuantity totalLottoQuantity;
    private final LottoQuantity manualLottoQuantity;
    private Lottos totalLottos;

    public LottoGame(LottoQuantity totalLottoQuantity, LottoQuantity manualLottoQuantity, Lottos manualLottos) {
        this.totalLottoQuantity = totalLottoQuantity;
        this.manualLottoQuantity = manualLottoQuantity;
        this.totalLottos = manualLottos;
    }

    public Lottos createAutoLottos(LottoNumberGenerateStrategy lottoNumberGenerator) {
        LottoQuantity autoLottoQuantity = getAutoLottoQuantity();
        Lottos generated = new Lottos(autoLottoQuantity, lottoNumberGenerator);
        totalLottos = Lottos.concat(totalLottos, generated);

        return totalLottos;
    }

    public WinningResult createWinningResult(WinningLotto winningLotto) {
        return new WinningResult(totalLottos, winningLotto);
    }

    public LottoQuantity getManualLottoQuantity() {
        return manualLottoQuantity;
    }

    public LottoQuantity getAutoLottoQuantity() {
        return totalLottoQuantity.subtract(LottoQuantity.from(totalLottos.getSize()));
    }
}
