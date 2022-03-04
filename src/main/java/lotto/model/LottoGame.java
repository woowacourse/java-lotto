package lotto.model;

import java.util.List;

import lotto.model.numbergenerator.LottoNumberGenerator;

public class LottoGame {
    private final Lottos lottos;
    private final LottoMoney lottoMoney;

    public LottoGame(int lottoMoney, int numberOfManualLottos, LottoNumberGenerator lottoNumberGenerator) {
        this.lottoMoney = new LottoMoney(lottoMoney, numberOfManualLottos);
        this.lottos = buyAutoLottos(lottoNumberGenerator);
    }

    private Lottos buyAutoLottos(LottoNumberGenerator lottoNumberGenerator) {
        return new Lottos(lottoNumberGenerator, lottoMoney.getAutoLottoSize());
    }

    public LottoResult generateLottoResult(List<Integer> winningNumbers, int bonusNumber) {
        return new LottoResult(lottos, winningNumbers, bonusNumber);
    }

    public Yield calculateYield(LottoResult lottoResult) {
        return lottoResult.calculateYield(lottoMoney);
    }

    public List<Lotto> getLottos() {
        return lottos.getLottos();
    }
}
