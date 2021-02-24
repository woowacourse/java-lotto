import domain.bettingMoney.BettingMoney;
import domain.lotto.LottoTickets;
import domain.lotto.TicketCount;
import domain.lotto.WinningLotto;
import domain.result.Result;
import service.LottoService;
import util.InputUtil;
import view.LottoGameScreen;
import view.dto.LottoGameResultDto;

import java.util.Set;

public class GameManageApplication {
    private final LottoGameScreen lottoGameScreen;
    private final LottoService lottoService;

    public GameManageApplication(final LottoGameScreen lottoGameScreen, LottoService lottoService) {
        this.lottoGameScreen = lottoGameScreen;
        this.lottoService = lottoService;
    }

    public void run() {
        BettingMoney bettingMoney = getBettingMoney();
        TicketCount ticketCount = getTicketCount(bettingMoney);
        lottoGameScreen.showTicketCount(ticketCount);
        LottoTickets lottoTickets = lottoService.getLottoTickets(bettingMoney);
        lottoGameScreen.showAllLottoStatus(lottoTickets.getLottoTickets());
        WinningLotto winningLotto = getWinningLotto();

        Result result = new Result(lottoTickets, winningLotto);
        lottoGameScreen.showGameResult(new LottoGameResultDto(result.getResults()));
        lottoGameScreen.showRevenueResult(result.findEarningsRate(bettingMoney));
    }

    private BettingMoney getBettingMoney() {
        int input = InputUtil.inputBettingMoney();
        return BettingMoney.of(input);
    }

    private TicketCount getTicketCount(final BettingMoney bettingMoney) {
        int ticketCount = bettingMoney.getTicketCount();
        return new TicketCount(ticketCount);
    }

    private WinningLotto getWinningLotto() {
        Set<Integer> winningNumbers = InputUtil.inputWinningNumbers();
        int bonusNumber = InputUtil.inputBonusNumber();
        return new WinningLotto(winningNumbers, bonusNumber);
    }
}
