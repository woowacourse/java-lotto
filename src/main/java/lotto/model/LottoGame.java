package lotto.model;

import java.util.List;
import java.util.Set;

import lotto.model.numbergenerator.LottoNumberGenerator;
import lotto.model.numbergenerator.ManualGenerator;

public class LottoGame {
    private final Lottos autoLottos;
    private final LottoMoney lottoMoney;

    public LottoGame(long lottoMoney, int numberOfManualLottos, LottoNumberGenerator lottoNumberGenerator) {
        this.lottoMoney = new LottoMoney(lottoMoney, numberOfManualLottos);
        this.autoLottos = buyAutoLottos(lottoNumberGenerator);
    }

    private Lottos buyAutoLottos(LottoNumberGenerator lottoNumberGenerator) {
        return new Lottos(lottoNumberGenerator, lottoMoney.getAutoLottoSize());
    }

    public Lottos buyManualLottos(List<Set<Integer>> inputManualLottos) {
        return new Lottos(new ManualGenerator(inputManualLottos), inputManualLottos.size());
    }

    public LottoResult generateLottoResult(Lottos manualLottos, Set<Integer> winningNumbers, int bonusNumber) {
        return new LottoResult(manualLottos, autoLottos, winningNumbers, bonusNumber);
    }

    public Yield calculateYield(LottoResult lottoResult) {
        return lottoResult.calculateYield(lottoMoney);
    }

    public List<Lotto> getAutoLottos() {
        return autoLottos.getLottos();
    }
}
