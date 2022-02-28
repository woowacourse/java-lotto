package lotto.model.lotto;

import lotto.model.bonusball.BonusBallResponse;
import lotto.model.result.RateOfReturn;
import lotto.model.bonusball.BonusBall;
import lotto.model.result.WinningResult;
import lotto.model.winningnumber.WinningNumber;
import lotto.model.winningnumber.WinningNumberResponse;

public class LottoGame {

    public LottoStorage makeLottos(LottoCount lottoCount) {
        return new LottoStorage(lottoCount);
    }

    public RateOfReturn storeMoneyInRateOfReturn(String money) {
        return new RateOfReturn(money);
    }

    public BonusBall storeBonusBall(WinningNumber WinningNumber, String input) {
        BonusBall bonusBall = new BonusBall(input);
        WinningNumber.validateReduplicationWithBonusBall(input);
        return bonusBall;
    }

    public WinningResult calcWinningNumber(LottoStorage lottoStorage, BonusBallResponse bonusBallResponse,
                                                    WinningNumberResponse WinningNumberResponse) {
        return lottoStorage.calcWinningNumber(bonusBallResponse, WinningNumberResponse);
    }

    public double sendRateOfReturn(RateOfReturn rateOfReturn, WinningResult winningResult) {
        return rateOfReturn.calcRateOfReturn(winningResult);
    }
}
