package lotto.model;

import java.util.List;

import lotto.model.numbergenerator.LottoNumberGenerator;

public class LottoGame {
    private final Lottos lottos;
    private final LottoMoney lottoMoney;

    public LottoGame(int lottoMoney, LottoNumberGenerator lottoNumberGenerator) {
        this.lottoMoney = new LottoMoney(lottoMoney);
        this.lottos = buyLottos(lottoNumberGenerator);
    }

    private Lottos buyLottos(LottoNumberGenerator lottoNumberGenerator) {
        return new Lottos(lottoNumberGenerator, lottoMoney.getLottoSize());
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
