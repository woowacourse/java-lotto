package lotto.controller;

import lotto.model.money.Money;
import lotto.model.ticket.LottoTickets;
import lotto.model.ticket.number.LottoNumber;
import lotto.model.utils.RandomNumberGenerator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    public void run() {
        Money money = Money.of(InputView.requestMoney());
        LottoTickets lottoTickets = LottoTickets.buy(new RandomNumberGenerator(LottoNumber.MIN, LottoNumber.MAX), money);
        OutputView.outputTickets(lottoTickets);
    }
}
