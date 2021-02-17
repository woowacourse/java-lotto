package lotto.controller;

import lotto.domain.lotto.LottoGenerator;
import lotto.domain.lotto.LottoGroup;
import lotto.domain.lotto.WinningNumber;
import lotto.domain.number.PayOut;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private static final LottoGenerator lottoGenerator = new LottoGenerator();

    public static void run() {
        PayOut payOut = payOut();

        LottoGroup lottoGroup = buyLotto(payOut);

        WinningNumber winningNumber =
            new WinningNumber(inputLastWeekLottoNumber(), inputBonusNumber());

        calculateStatistics(winningNumber, lottoGroup, payOut);
    }

    private static PayOut payOut() {
        OutputView.payout();

        PayOut payOut = new PayOut(InputView.getStringInputFromUser());

        OutputView.payOuted(payOut.getGameCount());

        return payOut;
    }

    private static LottoGroup buyLotto(PayOut payOut) {
        LottoGroup lottoGroup = lottoGenerator.generateLotties(payOut.getGameCount());
        OutputView.boughtLotties(lottoGroup);

        return lottoGroup;
    }

    private static String inputLastWeekLottoNumber() {
        OutputView.lastWeekLottoNumber();

        return InputView.getStringInputFromUser();
    }

    private static String inputBonusNumber() {
        OutputView.bonusNumber();

        return InputView.getStringInputFromUser();
    }

    private static void calculateStatistics(
        WinningNumber winningNumber,
        LottoGroup lottoGroup,
        PayOut payOut) {
        OutputView.statistics(winningNumber.getResult(lottoGroup, payOut));
    }
}
