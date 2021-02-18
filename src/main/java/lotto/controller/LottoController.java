package lotto.controller;

import lotto.domain.lotto.LottoGenerator;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.WinningNumbers;
import lotto.domain.number.PayOut;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private static final LottoGenerator LOTTO_GENERATOR = LottoGenerator.getInstance();

    public static void run() {
        PayOut payOut = payOut();
        LottoTicket lottoTicket = buyLotto(payOut);
        WinningNumbers winningNumbers =
            WinningNumbers.valueOf(inputLastWeekLottoNumber(), inputBonusNumber());

        calculateStatistics(winningNumbers, lottoTicket, payOut);
    }

    private static PayOut payOut() {
        OutputView.payout();

        PayOut payOut = PayOut.valueOf(InputView.getStringInputFromUser());

        OutputView.payOuted(payOut.getGameCount());

        return payOut;
    }

    private static LottoTicket buyLotto(PayOut payOut) {
        LottoTicket lottoTicket = LOTTO_GENERATOR.newLottoTicket(payOut.getGameCount());
        OutputView.boughtLotties(lottoTicket);

        return lottoTicket;
    }

    private static String inputLastWeekLottoNumber() {
        OutputView.lastWeekLottoNumber();

        return InputView.getStringInputFromUser();
    }

    private static String inputBonusNumber() {
        OutputView.bonusNumber();

        return InputView.getStringInputFromUser();
    }

    private static void calculateStatistics(WinningNumbers winningNumbers, LottoTicket lottoTicket,
        PayOut payOut) {
        OutputView.statistics(winningNumbers.getResult(lottoTicket, payOut));
    }
}
