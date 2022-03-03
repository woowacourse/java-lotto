package lotto.model.lotto;

import java.util.List;

import lotto.model.result.RateOfReturn;
import lotto.model.result.WinningResult;
import lotto.model.winningnumber.WinningLottoResponse;

public class LottoGame {

    public void makeManualLottos(List<Lotto> lottos, Lotto lotto, ManualCount manualCount) {
        lottos.add(lotto);
        manualCount.createManualLotto();
    }

    public boolean isPossibleMakeLottos(ManualCount manualCount) {
        return manualCount.isEnd();
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
