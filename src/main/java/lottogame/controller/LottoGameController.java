package lottogame.controller;

import java.util.Set;
import lottogame.domain.LottoGame;
import lottogame.domain.LottoGameResult;
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
        LottoTickets lottoTickets = getLottoTickets(new Money(InputView.inputMoney()));
        OutputView.printLottoTickets(lottoTickets.getLottoTickets());
        LottoWinningNumbers lottoWinningNumbers = getWinningNumbers();

        LottoGame lottoGame = new LottoGame(lottoTickets, lottoWinningNumbers);
        LottoGameResult lottoGameResult = lottoGame.getMatchingResult();
        OutputView.printLottoGameResult(lottoGameResult);
        OutputView.printLottoGameYield(lottoGame.getYield(lottoGameResult));
    }

    private LottoTickets getLottoTickets(Money money) {
        LottoTicketIssueMachine lottoTicketIssueMachine =
            new LottoTicketIssueMachine(new Money(money));
        return lottoTicketIssueMachine.issueTickets();
    }

    private LottoWinningNumbers getWinningNumbers() {
        Set<Integer> winningNumbers = InputView.inputWinningNumbers();
        int bonusNumber = InputView.inputBonusNumber();
        return new LottoWinningNumbers(winningNumbers, bonusNumber);
    }
}
