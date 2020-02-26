package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
    public static void main(String[] args) {
        LottoMoney lottoMoney = new LottoMoney(InputView.requestLottoMoneyInput());
        LottoCount lottoCount = new LottoCount(lottoMoney, InputView.requestManualLottoCount());
        Lottos lottos = new Lottos(lottoMoney);
        OutputView.printLottoCountAndLottos(lottos);

        Lotto winningLotto = Lotto.from(InputView.requestWinningLottoInput());
        LottoNumber bonusNumber = LottoNumber.of(InputView.requestBonusNumberInput());

        MatchResults matchResults = lottos.toMatchResults(winningLotto, bonusNumber);
        int earningsRate = calculateEarningsRate(matchResults, lottoMoney);
        OutputView.printLottoResultAndEarningsRate(matchResults, earningsRate);
    }

    private static int calculateEarningsRate(MatchResults matchResults, LottoMoney lottoMoney) {
        int totalEarnings = matchResults.calculateTotalEarnings();
        return lottoMoney.calculateEarningsRate(totalEarnings);
    }
}
