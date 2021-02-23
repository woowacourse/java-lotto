package lottogame.controller;

import java.util.List;
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
        Money money = new Money(InputView.inputMoney());
        int manualTicketCount = InputView.inputManualTicketCount();
        LottoTickets lottoTickets = getLottoTickets(money, manualTicketCount);
        LottoWinningNumbers lottoWinningNumbers = getWinningNumbers();

        LottoGame lottoGame = new LottoGame(lottoTickets, lottoWinningNumbers);
        LottoGameResult lottoGameResult = lottoGame.getMatchingResult();
        OutputView.printLottoGameResult(lottoGameResult);
        OutputView.printLottoGameYield(lottoGame.getYield(lottoGameResult));
    }

    private LottoTickets getLottoTickets(final Money money, final int manualTicketCount) {
        List<Set<Integer>> manualTicketNumbers = InputView.inputManualTicketNumbers(manualTicketCount);
        LottoTicketIssueMachine lottoTicketIssueMachine = new LottoTicketIssueMachine(money, manualTicketCount);
        LottoTickets manualTickets = lottoTicketIssueMachine.issueManualTickets(manualTicketNumbers);
        LottoTickets autoTickets = lottoTicketIssueMachine.issueAutoTickets();
        OutputView.printLottoTickets(manualTickets.getLottoTickets(), autoTickets.getLottoTickets());
        return manualTickets.joinLottoTickets(autoTickets);
    }

    private LottoWinningNumbers getWinningNumbers() {
        Set<Integer> winningNumbers = InputView.inputWinningNumbers();
        int bonusNumber = InputView.inputBonusNumber();
        return new LottoWinningNumbers(winningNumbers, bonusNumber);
    }
}
