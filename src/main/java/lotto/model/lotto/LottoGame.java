package lotto.model.lotto;

import java.util.ArrayList;
import java.util.List;

import lotto.dto.WinningLottoResponse;
import lotto.model.lotto.result.RateOfReturn;
import lotto.model.lotto.result.WinningResult;

public class LottoGame {

    public List<Lotto> makeManualLottos(List<List<Integer>> receivedLottos) {
        List<Lotto> lottos = new ArrayList<>();
        for (List<Integer> lotto : receivedLottos) {
            lottos.add(new Lotto(lotto));
        }
        return lottos;
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
