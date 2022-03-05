package lotto.model.lotto;

import java.util.List;

import lotto.dto.WinningLottoResponse;
import lotto.model.lotto.result.RateOfReturn;
import lotto.model.lotto.result.WinningResult;

public class LottoGame {

    public void makeManualLottos(List<Lotto> lottos, Lotto lotto) {
        lottos.add(lotto);
    }

    public LottoStorage makeLottos(LottoCount lottoCount, List<Lotto> lottos) {
        return new LottoStorage(lottoCount, lottos);
    }

    public RateOfReturn storeMoneyInRateOfReturn(long money) {
        return new RateOfReturn(money);
    }

    public WinningResult calcWinningNumber(LottoStorage lottoStorage, WinningLottoResponse winningLottoResponse) {
        return lottoStorage.calcWinningNumber(winningLottoResponse);
    }

    public double sendRateOfReturn(RateOfReturn rateOfReturn, WinningResult winningResult) {
        return rateOfReturn.calcRateOfReturn(winningResult);
    }
}
