package lotto.controller;

import lotto.domain.lotto.LottoGenerator;
import lotto.domain.lotto.LottoGroup;
import lotto.domain.lotto.WinningNumber;
import lotto.domain.number.PayOut;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    public static void run() {
        OutputView.payout();
        PayOut payOut = new PayOut(InputView.getStringInputFromUser());
        int lottoCount = payOut.getGameCount();
        OutputView.payOuted(lottoCount);
        LottoGenerator lottoGenerator = new LottoGenerator();
        LottoGroup lottoGroup = lottoGenerator.generateLotties(lottoCount);
        OutputView.boughtLotties(lottoGroup);
        OutputView.lastWeekLottoNumber();
        String lottoNumberInput = InputView.getStringInputFromUser();
        OutputView.bonusNumber();
        String bonusNumberInput = InputView.getStringInputFromUser();
        WinningNumber winningNumber = new WinningNumber(lottoNumberInput, bonusNumberInput);
        OutputView.statistics(winningNumber.getResult(lottoGroup, payOut));
    }
}
