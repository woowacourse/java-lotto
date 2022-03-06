package lotto.model;

import java.util.List;

public class LottoGame {
    private final Lottos autoLottos;
    private final LottoMoney lottoMoney;

    public LottoGame(long lottoMoney, int numberOfManualLottos, LottoFactory autoLottoFactory) {
        this.lottoMoney = new LottoMoney(lottoMoney, numberOfManualLottos);
        this.autoLottos = buyAutoLottos(autoLottoFactory);
    }

    private Lottos buyAutoLottos(LottoFactory autoLottoFactory) {
        return new Lottos(autoLottoFactory, lottoMoney.getAutoLottoSize());
    }

    public Lottos buyManualLottos(List<List<Integer>> inputManualLottos) {
        return new Lottos(new ManualLottoFactory(inputManualLottos), inputManualLottos.size());
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
