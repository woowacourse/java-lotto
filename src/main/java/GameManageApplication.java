import domain.LottoMachine;
import domain.bettingMoney.BettingMoney;
import domain.lotto.LottoTicket;
import domain.lotto.LottoTickets;
import domain.lotto.TicketCount;
import domain.lotto.WinningLotto;
import domain.result.Result;
import view.InputView;
import view.LottoGameScreen;
import view.dto.LottoGameResultDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class GameManageApplication {
    private final LottoGameScreen lottoGameScreen;
    private final InputView inputView;

    public GameManageApplication(final LottoGameScreen lottoGameScreen, final InputView inputView) {
        this.lottoGameScreen = lottoGameScreen;
        this.inputView = inputView;
    }

    public void run() {
        BettingMoney bettingMoney = getBettingMoney();
        TicketCount ticketCount = bettingMoney.getTicketCount(LottoTicket.TICKET_PRICE);
        lottoGameScreen.showTicketCount(ticketCount);

        LottoTickets lottoTickets = getLottoTickets(ticketCount);

        viewGameResult(bettingMoney, lottoTickets);
    }

    private BettingMoney getBettingMoney() {
        int input = inputView.inputBettingMoney();
        return new BettingMoney(input);
    }

    private LottoTickets getLottoTickets(final TicketCount ticketCount) {
        int manualTicketCount = inputView.inputManualTicketCount();
        TicketCount randomTicketCount = ticketCount.reduceTicketCount(manualTicketCount);
        List<List<Integer>> manualTicketsNumbers = getManualTicketsNumbers(manualTicketCount);

        LottoMachine lottoMachine = new LottoMachine();
        LottoTickets lottoTickets = lottoMachine.makeLottoTickets(manualTicketsNumbers, randomTicketCount);
        lottoGameScreen.showAllLottoStatus(lottoTickets.getLottoTickets());
        return lottoTickets;
    }

    private List<List<Integer>> getManualTicketsNumbers(final int manualTicketCount) {
        if (manualTicketCount == 0) {
            return new ArrayList<>(Collections.emptyList());
        }
        return inputView.inputManualTicketNumber(manualTicketCount);
    }

    private void viewGameResult(BettingMoney bettingMoney, LottoTickets lottoTickets) {
        WinningLotto winningLotto = getWinningLotto();
        Result result = new Result(lottoTickets, winningLotto);
        lottoGameScreen.showGameResult(new LottoGameResultDto(result.getResults()));
        lottoGameScreen.showRevenueResult(result.findEarningsRate(bettingMoney));
    }

    private WinningLotto getWinningLotto() {
        Set<Integer> winningNumbers = inputView.inputWinningNumbers();
        int bonusNumber = inputView.inputBonusNumber();
        return new WinningLotto(winningNumbers, bonusNumber);
    }
}
