import domain.bettingMoney.BettingMoney;
import domain.lotto.LottoTickets;
import domain.lotto.TicketCount;
import domain.lotto.WinningLotto;
import domain.result.Result;
import service.LottoService;
import view.InputView;
import view.LottoGameScreen;
import view.dto.LottoGameResultDto;

import java.util.Set;

public class GameManageApplication {
    private final LottoGameScreen lottoGameScreen;
    private final LottoService lottoService;
    private final InputView inputView;

    public GameManageApplication(final LottoGameScreen lottoGameScreen, LottoService lottoService, InputView inputView) {
        this.lottoGameScreen = lottoGameScreen;
        this.lottoService = lottoService;
        this.inputView = inputView;
    }

    public void run() {
        BettingMoney bettingMoney = getBettingMoney();
        TicketCount ticketCount = getTicketCount(bettingMoney);
        lottoGameScreen.showTicketCount(ticketCount);
        LottoTickets lottoTickets = lottoService.getLottoTickets(inputView, ticketCount);
        lottoGameScreen.showAllLottoStatus(lottoTickets.getLottoTickets());

        WinningLotto winningLotto = getWinningLotto();
        Result result = new Result(lottoTickets, winningLotto);
        lottoGameScreen.showGameResult(new LottoGameResultDto(result.getResults()));
        lottoGameScreen.showRevenueResult(result.findEarningsRate(bettingMoney));
    }

    private BettingMoney getBettingMoney() {
        int input = inputView.inputBettingMoney();
        return new BettingMoney(input);
    }

    private TicketCount getTicketCount(final BettingMoney bettingMoney) {
        int ticketCount = bettingMoney.getTicketCount();
        return new TicketCount(ticketCount);
    }

    private WinningLotto getWinningLotto() {
        Set<Integer> winningNumbers = inputView.inputWinningNumbers();
        int bonusNumber = inputView.inputBonusNumber();
        return new WinningLotto(winningNumbers, bonusNumber);
    }
}
