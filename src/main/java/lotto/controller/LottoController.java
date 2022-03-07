package lotto.controller;

import java.util.List;
import lotto.domain.LottoNumber;
import lotto.domain.LottoStatistics;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.ManualCount;
import lotto.domain.Money;
import lotto.domain.LottoTicketGroup;
import lotto.utils.RandomNumberGenerator;
import lotto.domain.WinningTicket;
import lotto.dto.LottoTicketResponse;
import lotto.dto.StatisticsResult;
import lotto.utils.StringNumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Money money = new Money(inputView.inputMoney());
        ManualCount manualCount = ManualCount.of(money, inputView.inputManualLottoCount());

        LottoTicketGroup lottoTickets = createLottoTickets(money, manualCount);
        WinningTicket winningTicket = getWinningTicket();

        StatisticsResult result = getStatisticsResult(money, lottoTickets, winningTicket);
        outputView.outputStatisticsResult(result);
        outputView.outputEarningRate(result.getEarningRate());
    }

    private LottoTicketGroup createLottoTickets(Money money, ManualCount manualCount) {
        List<String> inputManualLottoTickets = inputView.inputManualLottoTickets(
            manualCount.getCount());
        Money changes = money.changes(manualCount.getCount());
        LottoTicketGroup lottoTickets = getLottoTickets(inputManualLottoTickets, changes, manualCount);
        List<LottoTicketResponse> lottoTicketsResponse = LottoTicketResponse.from(lottoTickets);

        outputView.outputLottoCount(manualCount.getCount(), changes.count());
        outputView.outputTickets(lottoTicketsResponse);

        return lottoTickets;
    }

    private LottoTicketGroup getLottoTickets(List<String> inputManualLottoTickets, Money changes,
        ManualCount manualCount) {
        LottoTickets manualTickets = getManualTickets(inputManualLottoTickets, manualCount);
        LottoTickets autoTickets = getAutoLottoTickets(changes);
        return new LottoTicketGroup(manualTickets, autoTickets);
    }

    private LottoTickets getManualTickets(List<String> inputManualLottoTickets,
        ManualCount manualCount) {
        return LottoTickets.buyTicket(
            new StringNumberGenerator(inputManualLottoTickets),
            manualCount.getCount());
    }

    private LottoTickets getAutoLottoTickets(Money changes) {
        return LottoTickets.buyTicket(
            new RandomNumberGenerator(LottoNumber.MIN, LottoNumber.MAX),
            changes.count()
        );
    }

    private WinningTicket getWinningTicket() {
        return new WinningTicket(
            LottoTicket.createTicket(new StringNumberGenerator(inputView.inputWinningNumber())),
            new LottoNumber(inputView.inputBonusBall()));
    }

    private StatisticsResult getStatisticsResult(Money money, LottoTicketGroup lottoTickets,
        WinningTicket winningTicket) {
        LottoStatistics lottoStatistics = lottoTickets.findLottoWinners(winningTicket);
        String earningRate = lottoStatistics.calculateEarningRates(money);
        return new StatisticsResult(lottoStatistics.getStatisticsByRank(),
            earningRate);
    }
}
