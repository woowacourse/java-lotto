package lotto.controller;

import java.util.List;
import lotto.domain.LottoNumber;
import lotto.domain.LottoStatistics;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.domain.RandomNumberGenerator;
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
        LottoTickets lottoTickets = createLottoTickets(money);
        WinningTicket winningTicket = new WinningTicket(
            LottoTicket.createWinningTicket(inputView.inputWinningNumber()),
            new LottoNumber(inputView.inputBonusBall()));

        StatisticsResult result = getStatisticsResult(money, lottoTickets, winningTicket);
        outputView.outputStatisticsResult(result);
        outputView.outputEarningRate(result.getEarningRate());
    }

    private StatisticsResult getStatisticsResult(Money money, LottoTickets lottoTickets,
        WinningTicket winningTicket) {
        LottoStatistics lottoStatistics = lottoTickets.findLottoWinners(winningTicket);
        double earningRate = lottoStatistics.calculateEarningRates(money);
        StatisticsResult result = new StatisticsResult(lottoStatistics.getStatisticsByRank(),
            earningRate);
        return result;
    }

    private LottoTickets createLottoTickets(Money money) {
        LottoTickets lottoTickets = LottoTickets.buy(new RandomNumberGenerator(LottoNumber.MIN,
            LottoNumber.MAX), money);
        List<LottoTicketResponse> lottoTicketsResponse = LottoTicketResponse.from(
            lottoTickets);
        outputView.outputTickets(lottoTicketsResponse);
        return lottoTickets;
    }
}
