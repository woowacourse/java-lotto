package lotto.controller;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.EarningRate;
import lotto.domain.Ticket;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lotteries;
import lotto.domain.lottomachine.RandomLottoMachine;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.Money;
import lotto.domain.rating.RatingInfo;
import lotto.domain.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void start() {
        final Money money = getMoney();
        final Ticket ticket = new Ticket(money, manualBuyTicket());

        final Lotteries lotteries = generateLotto(ticket);
        OutputView.printBuyLotto(ticket.getManualCount(), ticket.getAutoCount());
        OutputView.printLottoResults(lotteries);

        final RatingInfo ratingInfo = lotteries.scratchLotto(buyWinningLotto());
        OutputView.printWinningStats(ratingInfo,
            new EarningRate().calculate(ratingInfo, money.getValue()));
    }

    private Money getMoney() {
        try {
            return tryGetMoney();
        } catch (IllegalArgumentException e) {
            OutputView.getMessage(e.getMessage());
            return getMoney();
        }
    }

    private Money tryGetMoney() {
        final int money = InputView.getMoney();
        return new Money(money);
    }

    private int manualBuyTicket() {
        try {
            return tryManualBuyTicket();
        } catch (IllegalArgumentException e) {
            OutputView.getMessage(e.getMessage());
            return manualBuyTicket();
        }
    }

    private int tryManualBuyTicket() {
        return InputView.getManualTicketCount();
    }

    private Lotteries generateLotto(final Ticket ticket) {
        final List<Lotto> lottos = generateManualLottoNumbers(ticket.getManualCount());
        return new Lotteries(lottos, new RandomLottoMachine(), ticket.getAutoCount());
    }

    private List<Lotto> generateManualLottoNumbers(final int count) {
        OutputView.getMessage(InputView.INPUT_MANUAL_BUY_NUMBERS_MESSAGE);
        final List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            final Lotto lotto = manualBuyLotto();
            lottos.add(lotto);
        }
        return lottos;
    }

    private Lotto manualBuyLotto() {
        try {
            return tryManualBuyLotto();
        } catch (IllegalArgumentException e) {
            OutputView.getMessage(e.getMessage());
            return manualBuyLotto();
        }
    }

    private Lotto tryManualBuyLotto() {
        return Lotto.from(InputView.getNumbers());
    }

    private WinningLotto buyWinningLotto() {
        try {
            return tryBuyWinningLotto();
        } catch (IllegalArgumentException e) {
            OutputView.getMessage(e.getMessage());
            return buyWinningLotto();
        }
    }

    private WinningLotto tryBuyWinningLotto() {
        final Lotto lotto = Lotto.from(InputView.getWinningNumbers());
        final LottoNumber bonusNumber = LottoNumber.from(InputView.getBonusBall());
        return new WinningLotto(lotto, bonusNumber);
    }

}
