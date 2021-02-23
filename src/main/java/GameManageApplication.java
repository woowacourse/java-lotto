import domain.bettingMoney.BettingMoney;
import domain.lotto.LottoTickets;
import domain.lotto.TicketCount;
import domain.lotto.WinningLotto;
import domain.result.Result;
import service.LottoService;
import util.InputUtil;
import view.LottoGameScreen;
import view.MainScreen;
import view.dto.LottoGameResultDto;

import java.util.Set;

public class GameManageApplication {
    private final MainScreen mainScreen;
    private final LottoGameScreen lottoGameScreen;
    private final LottoService lottoService;

    public GameManageApplication(final MainScreen mainScreen, final LottoGameScreen lottoGameScreen, LottoService lottoService) {
        this.mainScreen = mainScreen;
        this.lottoGameScreen = lottoGameScreen;
        this.lottoService = lottoService;
    }

    public void run() {
        BettingMoney bettingMoney = getBettingMoney();
        TicketCount ticketCount = getTicketCount(bettingMoney);
        mainScreen.showTicketCount(ticketCount);
        LottoTickets lottoTickets = lottoService.getLottoTickets(bettingMoney);
        lottoGameScreen.showAllLottoStatus(lottoTickets.getLottoTickets());
        WinningLotto winningLotto = getWinningLotto();

        Result result = new Result(lottoTickets, winningLotto);
        lottoGameScreen.showGameResult(new LottoGameResultDto(result.getResults()));
        lottoGameScreen.showRevenueResult(result.findEarningsRate(bettingMoney));
    }

    private BettingMoney getBettingMoney() {
        mainScreen.showInputMoney();
        int input = InputUtil.nextInt();
        return BettingMoney.of(input);
    }

    private TicketCount getTicketCount(final BettingMoney bettingMoney) {
        int ticketCount = bettingMoney.getTicketCount();
        return TicketCount.of(ticketCount);
    }

    private WinningLotto getWinningLotto() {
        lottoGameScreen.confirmWinningLotto();
        Set<Integer> winningNumbers = InputUtil.inputWinningNumbers();
        lottoGameScreen.confirmBonusLotto();
        int bonusNumber = InputUtil.inputBonusNumber();
        return new WinningLotto(winningNumbers, bonusNumber);
    }
}
