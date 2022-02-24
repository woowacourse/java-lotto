package lotto.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lotto.model.money.Money;
import lotto.model.result.LottoRank;
import lotto.model.result.LottoRanks;
import lotto.model.result.LottoStatistics;
import lotto.model.ticket.LottoTicket;
import lotto.model.ticket.LottoTickets;
import lotto.model.ticket.WinningTicket;
import lotto.model.ticket.number.LottoNumber;
import lotto.model.utils.RandomNumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    public void run() {
        Money money = Money.of(InputView.requestMoney());
        LottoTickets lottoTickets = LottoTickets
                .buy(new RandomNumberGenerator(LottoNumber.MIN, LottoNumber.MAX), money);
        OutputView.outputTickets(lottoTickets);
        WinningTicket winningTicket = makeWinningTicket();

        LottoRanks lottoRanks = lottoTickets.compareResult(winningTicket);
        LottoStatistics lottoStatistics = new LottoStatistics(lottoRanks);
        OutputView.outputStatistics(lottoStatistics, money);
    }

    private WinningTicket makeWinningTicket() {
        int[] winningNumbers = InputView.requestWinningNumber();
        int bonusBall = InputView.requestBonusBall();

        return new WinningTicket(
                new LottoTicket(Arrays.stream(winningNumbers)
                        .mapToObj(LottoNumber::new)
                        .collect(Collectors.toList())),
                new LottoNumber(bonusBall));
    }
}
