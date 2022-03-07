package domain;

import domain.strategy.LottoNumberGenerateStrategy;
import java.util.List;
import java.util.Set;

public class LottoGame {
    private final LottoQuantity totalLottoQuantity;
    private final LottoQuantity manualLottoQuantity;
    private Lottos totalLottos = Lottos.from(List.of());

    public LottoGame(LottoQuantity totalLottoQuantity, LottoQuantity manualLottoQuantity) {
        this.totalLottoQuantity = totalLottoQuantity;
        this.manualLottoQuantity = manualLottoQuantity;
    }

    public Lottos createManualLottos(List<Set<Integer>> rawLottosValue) {
        Lottos manualLottos = Lottos.fromRawValues(rawLottosValue);
        totalLottos = Lottos.concat(totalLottos, manualLottos);

        return totalLottos;
    }

    public Lottos createAutoLottos(LottoNumberGenerateStrategy lottoNumberGenerator) {
        LottoQuantity autoLottoQuantity = getAutoLottoQuantity();
        Lottos generated = Lottos.of(autoLottoQuantity, lottoNumberGenerator);
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
