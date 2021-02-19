package lotto.controller;

import lotto.domain.lotto.LottoGenerator;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.WinningNumbers;
import lotto.domain.lotto.WinningStatistics;
import lotto.domain.number.Payout;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private static final LottoGenerator LOTTO_GENERATOR = LottoGenerator.getInstance();

    public static void run() {
        Payout payout = inputPayout();
        LottoTicket lottoTicket = buyLotto(payout);
        WinningNumbers winningNumbers =
            WinningNumbers.valueOf(inputLastWeekLottoNumber(), inputBonusNumber());

        calculateStatistics(winningNumbers, lottoTicket);
    }

    private static Payout inputPayout() {
        OutputView.payout();
        Payout payOut = Payout.valueOf(InputView.getStringInputFromUser());

        return payOut;
    }

    private static LottoTicket buyLotto(Payout payout) {
        LottoTicket lottoTicket = LottoTicket.valueOf(payout, LOTTO_GENERATOR);
        OutputView.payOuted(lottoTicket.getCount());
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

    private static void calculateStatistics(WinningNumbers winningNumbers,
        LottoTicket lottoTicket) {
        OutputView.statistics(new WinningStatistics(lottoTicket, winningNumbers));
    }
}
