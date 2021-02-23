package lotto.controller;

import lotto.domain.lotto.LottoExchange;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.WinningNumbers;
import lotto.domain.number.ManualCount;
import lotto.domain.number.Payout;
import lotto.domain.rank.Ranks;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private static final LottoExchange LOTTO_EXCHANGE = LottoExchange.getInstance();

    public static void run() {
        Payout payout = inputPayout();
        ManualCount manualCount = inputManualCount(payout);
        LottoTicket lottoTicket = buyLotto(payout, manualCount);
        WinningNumbers winningNumbers =
            WinningNumbers.valueOf(inputLastWeekLottoNumber(), inputBonusNumber());

        calculateStatistics(winningNumbers, lottoTicket);
    }

    private static Payout inputPayout() {
        OutputView.payout();

        return Payout.valueOf(InputView.getStringInputFromUser());
    }

    private static ManualCount inputManualCount(Payout payout) {
        OutputView.manualCount();

        return ManualCount.valueOf(InputView.getStringInputFromUser());
    }

    private static LottoTicket buyLotto(Payout payout, ManualCount manualCount) {
        LottoTicket lottoTicket = LOTTO_EXCHANGE.buyLottoTicket(payout, manualCount);
        OutputView.payOuted(lottoTicket.count());
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
        Ranks ranks = lottoTicket.calculateRanks(winningNumbers);
        OutputView.statistics(ranks);
        OutputView.printYield(LOTTO_EXCHANGE.calculateYield(ranks));
    }
}
