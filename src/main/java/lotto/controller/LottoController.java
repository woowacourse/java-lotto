package lotto.controller;

import lotto.domain.lotto.*;
import lotto.domain.number.ManualPurchasesNumber;
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

        ManualPurchasesNumber manualLottoCount = getManualLottoCountFromUser(payOut);
        List<String> manualLottos = getManualLottosFromUser(payOut, manualLottoCount);

        LottoGroup lottoGroup = createLottosAccordingToTheAmount(manualLottos,
                payOut.subtractionUsingGameCount(manualLottoCount.getValueAsInt())
        );

        createWinningNumberAndlottoResultAnalysisAndPrint(lottoGroup, payOut);
    }

    private static PayOut getPayOutFromUser() {
        OutputView.payout();

        PayOut payOut = new PayOut(InputView.getStringInputFromUser());

        return payOut;
    }

    private static ManualPurchasesNumber getManualLottoCountFromUser(PayOut payOut) {
        OutputView.manualPurchase();

        return new ManualPurchasesNumber(InputView.getStringInputFromUser(), payOut);
    }

    private static List<String> getManualLottosFromUser(PayOut payOut, Number manualLottoCount) {
        OutputView.manualLottoNumber();

        List<String> manualLottos = Stream.generate(InputView::getStringInputFromUser)
                .limit(manualLottoCount.getValueAsInt())
                .collect(toList());

        OutputView.payOuted(payOut.getGameCount(), manualLottoCount.getValueAsInt());

        return manualLottos;
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

    private static void createWinningNumberAndlottoResultAnalysisAndPrint(
            LottoGroup lottoGroup,
            PayOut payOut) {

        WinningNumber winningNumber = new WinningNumber(
                getLastWeekLottoNumberFromUser(),
                getBonusNumberFromUser()
        );

        AnalysedLottos analysedLottos = winningNumber.analysingLottos(lottoGroup, payOut);
        OutputView.statistics(analysedLottos);
    }
}
