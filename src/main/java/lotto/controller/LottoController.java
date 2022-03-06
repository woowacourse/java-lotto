package lotto.controller;


import java.util.List;
import java.util.stream.Collectors;
import lotto.model.money.Money;
import lotto.model.result.LottoRanks;
import lotto.model.result.LottoStatistics;
import lotto.model.ticket.LottoTicket;
import lotto.model.ticket.LottoTickets;
import lotto.model.ticket.WinningTicket;
import lotto.model.ticket.buy.ManualBuyCount;
import lotto.model.ticket.number.LottoNumber;
import lotto.model.utils.RandomNumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void run() {
        Money money = Money.of(InputView.requestMoney());
        ManualBuyCount manualBuyCount = ManualBuyCount.of(InputView.requestManualBuyCount());
        LottoTickets lottoTickets = createLottoTickets(money, manualBuyCount);
        OutputView.outputTickets(lottoTickets);

        WinningTicket winningTicket = makeWinningTicket();
        LottoRanks lottoRanks = lottoTickets.compareResult(winningTicket);

        LottoStatistics lottoStatistics = new LottoStatistics(lottoRanks);
        OutputView.outputStatistics(lottoStatistics, money);
    }

    private LottoTickets createLottoTickets(Money money, ManualBuyCount manualBuyCount) {
        LottoTickets manualLottoTickets = LottoTickets.buyManualTickets(InputView.requestManualTickets(manualBuyCount));
        Money leftMoney = money.decreaseByCount(manualBuyCount);
        return manualLottoTickets.buyAutoTickets(new RandomNumberGenerator(LottoNumber.MIN_LOTTO_NUMBER, LottoNumber.MAX_LOTTO_NUMBER), leftMoney);
    }

    private WinningTicket makeWinningTicket() {
        List<Integer> winningNumbers = InputView.requestWinningNumber();
        int bonusBall = InputView.requestBonusBall();

        return new WinningTicket(
                new LottoTicket(winningNumbers
                        .stream()
                        .map(LottoNumber::from)
                        .collect(Collectors.toList())),
                LottoNumber.from(bonusBall));
    }
}
