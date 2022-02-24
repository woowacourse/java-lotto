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

        LottoStatistics lottoStatistics = lottoTickets.findLottoWinners(winningTicket);
        double earningRate = lottoStatistics.calculateEarningRates(money);
        StatisticsResult result = new StatisticsResult(lottoStatistics.getMap(), earningRate);
        outputView.outputStatisticsResult(result);
        outputView.outputEarningRate(result.getEarningRate());
    }

    private LottoTickets createLottoTickets(Money money) {
        RandomNumberGenerator generator = new RandomNumberGenerator(LottoNumber.MIN,
            LottoNumber.MAX);
        LottoTickets lottoTickets = LottoTickets.buy(generator, money);
        List<LottoTicketResponse> lottoTicketsResponse = LottoTicketResponse.from(
            lottoTickets);
        outputView.outputTickets(lottoTicketsResponse);
        return lottoTickets;
    }
}
