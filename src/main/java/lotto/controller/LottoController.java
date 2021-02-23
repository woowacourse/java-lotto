package lotto.controller;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Stream;
import lotto.domain.lotto.LottoExchange;
import lotto.domain.lotto.LottoNumbers;
import lotto.domain.lotto.LottoNumbersType;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.WinningNumbers;
import lotto.domain.number.LottoNumber;
import lotto.domain.number.ManualCount;
import lotto.domain.number.Payout;
import lotto.domain.rank.Ranks;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private static final LottoExchange LOTTO_EXCHANGE = LottoExchange.getInstance();

    public static void run() {
        Payout payout = inputPayout();
        ManualCount manualCount = inputManualCount();
        List<LottoNumbers> manualLottos = inputManual(manualCount);
        LottoTicket lottoTicket = buyLotto(payout, manualLottos);
        WinningNumbers winningNumbers =
            new WinningNumbers(LottoNumbers.valueOf(inputLastWeekLottoNumber()),
                LottoNumber.valueOf(inputBonusNumber()));

        calculateStatistics(winningNumbers, lottoTicket);
    }

    private static Payout inputPayout() {
        OutputView.payout();

        return Payout.valueOf(InputView.getStringInputFromUser());
    }

    private static ManualCount inputManualCount() {
        OutputView.manualCount();

        return ManualCount.valueOf(InputView.getStringInputFromUser());
    }

    private static List<LottoNumbers> inputManual(ManualCount manualCount) {
        OutputView.manual();

        return Stream.generate(InputView::getStringInputFromUser)
            .limit(manualCount.unwrap())
            .map(LottoNumbers::valueOf)
            .collect(toList());
    }

    private static LottoTicket buyLotto(Payout payout, List<LottoNumbers> manualLottos) {
        LottoTicket lottoTicket = LOTTO_EXCHANGE.buyLottoTicket(payout, manualLottos);
        OutputView.payOuted(lottoTicket.getCountByType(LottoNumbersType.MANUAL),
            lottoTicket.getCountByType(LottoNumbersType.AUTO));
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
