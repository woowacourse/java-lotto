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
    private static final LottoGenerator LOTTO_GENERATOR = new LottoGenerator();
    private static final GamePrice GAME_PRICE = new GamePrice();

    public static void run() {
        PayOut payOut = getPayOutFromUser();

        Number manualLottoCount = getManualLottoCountFromUser();
        List<String> manualLottos = getManualLottosFromUser(GAME_PRICE.getGameCount(payOut), manualLottoCount);

        LottoGroup lottoGroup = createLottosAccordingToTheAmount(manualLottos,
                GAME_PRICE.getGameCount(
                        GAME_PRICE.subtractPayOutByGameCount(payOut, manualLottoCount.getValueAsInt())
                )
        );

        WinningNumber winningNumber = createWinningNumber();
        createWinningNumberAndlottoResultAnalysisAndPrint(winningNumber, lottoGroup, payOut);
    }

    private static PayOut getPayOutFromUser() {
        OutputView.payout();

        return new PayOut(InputView.getStringInputFromUser());
    }

    private static Number getManualLottoCountFromUser() {
        OutputView.manualPurchase();

        return new Number(InputView.getStringInputFromUser());
    }

    private static List<String> getManualLottosFromUser(int gameCount, Number manualLottoCount) {
        OutputView.manualLottoNumber();

        List<String> manualLottos = Stream.generate(InputView::getStringInputFromUser)
                .limit(manualLottoCount.getValueAsInt())
                .collect(toList());

        OutputView.payOuted(gameCount, manualLottoCount.getValueAsInt());

        return manualLottos;
    }

    private static LottoGroup createLottosAccordingToTheAmount(List<String> manualLottos,
                                                               int gameCount) {
        LottoGroup lottoGroup = LOTTO_GENERATOR.generateLottosWithManualLottoNumbers(
                manualLottos,
                gameCount
        );

        OutputView.boughtLottos(lottoGroup);

        return lottoGroup;
    }

    public static WinningNumber createWinningNumber() {
        return new WinningNumber(
                getLastWeekLottoNumberFromUser(),
                getBonusNumberFromUser()
        );
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
            WinningNumber winningNumber,
            LottoGroup lottoGroup,
            PayOut payOut) {

        OutputView.printWinningStatistics(winningNumber.getRanks(lottoGroup));
        OutputView.printYield(winningNumber.getYield(lottoGroup, payOut));
    }
}
