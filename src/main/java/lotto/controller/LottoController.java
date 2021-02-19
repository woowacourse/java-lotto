package lotto.controller;

import lotto.domain.lotto.LottoGenerator;
import lotto.domain.lotto.LottoGroup;
import lotto.domain.lotto.WinningNumber;
import lotto.domain.lotto.AnalysedLottos;
import lotto.domain.number.PayOut;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private static final LottoGenerator lottoGenerator = new LottoGenerator();

    public static void run() {
        PayOut payOut = getPayOutFromUser();

        LottoGroup lottoGroup = createLottosAccordingToTheAmount(payOut);

        WinningNumber winningNumber = new WinningNumber(
                getLastWeekLottoNumberFromUser(),
                getBonusNumberFromUser()
        );

        lottoResultAnalysisAndPrint(winningNumber, lottoGroup, payOut);
    }

    private static PayOut getPayOutFromUser() {
        OutputView.payout();

        PayOut payOut = new PayOut(InputView.getStringInputFromUser());

        OutputView.payOuted(payOut.getGameCount());

        return payOut;
    }

    private static LottoGroup createLottosAccordingToTheAmount(PayOut payOut) {
        LottoGroup lottoGroup = lottoGenerator.generateLottos(payOut.getGameCount());
        OutputView.boughtLottos(lottoGroup);

        return lottoGroup;
    }

    private static String getLastWeekLottoNumberFromUser() {
        OutputView.lastWeekLottoNumber();

        return InputView.getStringInputFromUser();
    }

    private static String getBonusNumberFromUser() {
        OutputView.bonusNumber();

        return InputView.getStringInputFromUser();
    }

    private static void lottoResultAnalysisAndPrint(
            WinningNumber winningNumber,
            LottoGroup lottoGroup,
            PayOut payOut) {
        AnalysedLottos analysedLottos = winningNumber.analysingLottos(lottoGroup, payOut);
        OutputView.statistics(analysedLottos);
    }
}
