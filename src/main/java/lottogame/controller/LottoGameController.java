package lottogame.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;
import lottogame.domain.Count;
import lottogame.domain.LottoGame;
import lottogame.domain.Money;
import lottogame.domain.Rank;
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
        Count manualTicketCount = new Count(InputView.inputManualTicketCount());
        LottoTickets lottoTickets = getLottoTickets(money, manualTicketCount);
        LottoWinningNumbers lottoWinningNumbers = getWinningNumbers();

        LottoGame lottoGame = new LottoGame(lottoTickets, lottoWinningNumbers);
        Map<Rank, Integer> ranks =  lottoGame.getMatchingResult();
        OutputView.printLottoGameResult(ranks);
        OutputView.printLottoGameYield(lottoGame.getYield(ranks));
    }

    private LottoTickets getLottoTickets(final Money money, final Count manualTicketCount) {
        List<Set<Integer>> manualTicketNumbers = InputView.inputManualTicketNumbers(new Count(manualTicketCount));
        LottoTicketIssueMachine lottoTicketIssueMachine = new LottoTicketIssueMachine(new Money(money), new Count(manualTicketCount));
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
