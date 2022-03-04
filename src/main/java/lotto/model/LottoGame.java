package lotto.model;

import java.util.ArrayList;
import java.util.List;

import lotto.model.lottos.AutoLottos;
import lotto.model.lottos.Lottos;
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
        return new AutoLottos(lottoNumberGenerator, lottoMoney.getAutoLottoSize());
    }

    public Lottos buyManualLottos(List<List<Integer>> inputManualLottos) {
        return new Lottos(new ManualGenerator(inputManualLottos), inputManualLottos.size());
    }

    public LottoResult generateLottoResult(Lottos manualLottos, List<Integer> winningNumbers, int bonusNumber) {
        return new LottoResult(manualLottos, autoLottos, winningNumbers, bonusNumber);
    }

    public Yield calculateYield(LottoResult lottoResult) {
        return lottoResult.calculateYield(lottoMoney);
    }

    public List<Lotto> getAutoLottos() {
        return autoLottos.getLottos();
    }
}
