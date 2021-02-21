package lottogame.controller;

import java.util.Set;
import lottogame.domain.Money;
import lottogame.domain.machine.LottoTicketIssueMachine;
import lottogame.domain.number.LottoWinningNumbers;
import lottogame.domain.ticket.LottoTickets;
import lottogame.view.InputView;
import lottogame.view.OutputView;

public class LottoGameController {

    public LottoGameController() {
    }

    public void play() {
        Money money = new Money(InputView.inputMoney());
        LottoTickets lottoTickets = LottoTicketIssueMachine.issueTickets(new Money(money));
        OutputView.printLottoTickets(lottoTickets.getLottoTickets());

        Set<Integer> winningNumbers = InputView.inputWinningNumbers();
        int bonusNumber = InputView.inputBonusNumber();
        LottoWinningNumbers lottoWinningNumbers = new LottoWinningNumbers(winningNumbers, bonusNumber);


    }
}
