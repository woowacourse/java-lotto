package lotto.model.lotto;

import lotto.model.result.RateOfReturn;
import lotto.model.result.WinningResult;
import lotto.model.winningnumber.WinningLottoResponse;

public class LottoGame {

    public LottoStorage makeLottos(LottoCount lottoCount) {
        return new LottoStorage(lottoCount);
    }

    public RateOfReturn storeMoneyInRateOfReturn(String money) {
        return new RateOfReturn(money);
    }

    public WinningResult calcWinningNumber(LottoStorage lottoStorage, WinningLottoResponse winningLottoResponse) {
        return lottoStorage.calcWinningNumber(winningLottoResponse);
    }

    public double sendRateOfReturn(RateOfReturn rateOfReturn, WinningResult winningResult) {
        return rateOfReturn.calcRateOfReturn(winningResult);
    }
}
