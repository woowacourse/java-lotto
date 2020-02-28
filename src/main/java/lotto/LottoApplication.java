package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoApplication {
    public static void main(String[] args) {
        LottoMoney lottoMoney = new LottoMoney(InputView.requestLottoMoneyInput());
        LottoCount lottoCount = lottoMoney.toLottoCount(InputView.requestManualLottoCount());

        Lottos lottos = createLottos(lottoCount);
        OutputView.printLottoCountAndLottos(lottoCount, lottos);

        WinningLotto winningLotto = createWinningLotto();

        MatchResults matchResults = lottos.toMatchResults(winningLotto);
        int earningsRate = calculateEarningsRate(matchResults, lottoMoney);
        OutputView.printLottoResultAndEarningsRate(matchResults, earningsRate);
    }

    private static Lottos createLottos(LottoCount lottoCount) {
        List<String[]> manualLottoLines = InputView.requestManualLottoInput(lottoCount);
        Lottos autoLottos = new AutoLottos(lottoCount).generate();
        Lottos manualLottos = new ManualLottos(manualLottoLines).generate();
        return Lottos.combine(autoLottos, manualLottos);
    }

    private static WinningLotto createWinningLotto() {
        Lotto winningLottoLine = Lotto.from(InputView.requestWinningLottoInput());
        LottoNumber bonusNumber = LottoNumber.of(InputView.requestBonusNumberInput());
        return new WinningLotto(winningLottoLine, bonusNumber);
    }

    private static int calculateEarningsRate(MatchResults matchResults, LottoMoney lottoMoney) {
        int totalEarnings = matchResults.calculateTotalEarnings();
        return lottoMoney.calculateEarningsRate(totalEarnings);
    }
}
