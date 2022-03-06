package lotto.controller;

import java.util.List;
import lotto.domain.LottoNumber;
import lotto.domain.LottoStatistics;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.ManualCount;
import lotto.domain.Money;
import lotto.utils.RandomNumberGenerator;
import lotto.domain.WinningTicket;
import lotto.dto.LottoTicketResponse;
import lotto.dto.StatisticsResult;
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

        LottoTickets lottoTickets = createLottoTickets(money, manualCount);
        WinningTicket winningTicket = getWinningTicket();

        StatisticsResult result = getStatisticsResult(money, lottoTickets, winningTicket);
        outputView.outputStatisticsResult(result);
        outputView.outputEarningRate(result.getEarningRate());
    }

    private LottoTickets createLottoTickets(Money money, ManualCount manualCount) {
        List<String> manualLottoTickets = inputView.inputManualLottoTickets(manualCount.getCount());
        Money changes = money.changes(manualCount.getCount());
        LottoTickets lottoTickets = getLottoTickets(manualLottoTickets, changes);
        List<LottoTicketResponse> lottoTicketsResponse = LottoTicketResponse.from(lottoTickets);

        outputView.outputLottoCount(manualCount.getCount(), changes.count());
        outputView.outputTickets(lottoTicketsResponse);

        return lottoTickets;
    }

    private LottoTickets getLottoTickets(List<String> manualLottoTickets, Money changes) {
        LottoTickets lottoTickets = getRandomLottoTickets(changes);
        lottoTickets.generatorManualTickets(manualLottoTickets);
        return lottoTickets;
    }

    private LottoTickets getRandomLottoTickets(Money changes) {
        return LottoTickets.buyRandomTicket(
            new RandomNumberGenerator(LottoNumber.MIN, LottoNumber.MAX),
            changes
        );
    }

    private WinningTicket getWinningTicket() {
        return new WinningTicket(
            LottoTicket.createTicket(inputView.inputWinningNumber()),
            new LottoNumber(inputView.inputBonusBall()));
    }

    private StatisticsResult getStatisticsResult(Money money, LottoTickets lottoTickets,
        WinningTicket winningTicket) {
        LottoStatistics lottoStatistics = lottoTickets.findLottoWinners(winningTicket);
        String earningRate = lottoStatistics.calculateEarningRates(money);
        return new StatisticsResult(lottoStatistics.getStatisticsByRank(),
            earningRate);
    }
}
