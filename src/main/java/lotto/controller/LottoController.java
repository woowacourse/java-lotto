package lotto.controller;

import lotto.domain.lotto.*;
import lotto.domain.number.Number;
import lotto.domain.number.PayOut;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class LottoController {
    private static final LottoGenerator lottoGenerator = new LottoGenerator();

    public static void run() {
        PayOut payOut = getPayOutFromUser();

        Number manualLottoCount = getManualLottoCountFromUser();
        List<String> manualLottos = getManualLottosFromUser(manualLottoCount);
        OutputView.payOuted(payOut.getGameCount(), manualLottoCount.getValueAsInt());

        LottoGroup lottoGroup = createLottosAccordingToTheAmount(manualLottos,
                payOut.subtractionUsingGameCount(manualLottoCount.getValueAsInt())
        );

        WinningNumber winningNumber = new WinningNumber(
                getLastWeekLottoNumberFromUser(),
                getBonusNumberFromUser()
        );

        lottoResultAnalysisAndPrint(winningNumber, lottoGroup, payOut);
    }

    private static PayOut getPayOutFromUser() {
        OutputView.payout();

        PayOut payOut = new PayOut(InputView.getStringInputFromUser());

        return payOut;
    }

    private static Number getManualLottoCountFromUser() {
        OutputView.manualPurchase();

        return new Number(InputView.getStringInputFromUser());
    }

    private static List<String> getManualLottosFromUser(Number manualLottoCount) {
        OutputView.manualLottoNumber();

        return Stream.generate(InputView::getStringInputFromUser)
                .limit(manualLottoCount.getValueAsInt())
                .collect(toList());
    }

    private static LottoGroup createLottosAccordingToTheAmount(List<String> manualLottos,
                                                               PayOut payOut) {
        LottoGroup lottoGroup = lottoGenerator.generateLottosWithManualLottoNumbers(
                manualLottos,
                payOut
        );

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
