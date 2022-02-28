package lotto.model.lotto;

import java.util.List;

import lotto.model.bonusball.BonusBallResponse;
import lotto.model.result.RateOfReturn;
import lotto.model.bonusball.BonusBall;
import lotto.model.result.WinningResult;
import lotto.model.winningnumber.LottoWinningNumber;
import lotto.model.winningnumber.LottoWinningNumberResponse;

public class LottoGame {

    public LottoStorage makeLottos(LottoCount lottoCount) {
        return new LottoStorage(lottoCount);
    }

    public RateOfReturn storeMoneyInRateOfReturn(String money) {
        return new RateOfReturn(money);
    }

    public LottoWinningNumber storeWinningNumber(List<String> input) {
        return new LottoWinningNumber(input);
    }

    public BonusBall storeBonusBall(LottoWinningNumber lottoWinningNumber, String input) {
        BonusBall bonusBall = new BonusBall(input);
        lottoWinningNumber.validateReduplicationWithBonusBall(input);
        return bonusBall;
    }

    public WinningResult calcLottoWithWinningNumber(LottoStorage lottoStorage, BonusBallResponse bonusBallResponse,
                                                    LottoWinningNumberResponse lottoWinningNumberResponse) {
        return lottoStorage.calcWinningNumber(bonusBallResponse, lottoWinningNumberResponse);
    }

    public double sendRateOfReturn(RateOfReturn rateOfReturn, WinningResult winningResult) {
        return rateOfReturn.calcRateOfReturn(winningResult);
    }
}
