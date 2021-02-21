import domain.LottoGameMachine;
import domain.budget.Budget;
import domain.lotto.LottoTickets;
import domain.lotto.WinningLotto;
import util.InputUtil;
import view.LottoGameScreen;
import view.MainScreen;

public class GameManageApplication {
    private final MainScreen mainScreen;
    private final LottoGameScreen lottoGameScreen;

    public GameManageApplication(MainScreen mainScreen, LottoGameScreen lottoGameScreen) {
        this.mainScreen = mainScreen;
        this.lottoGameScreen = lottoGameScreen;
    }

    public void run() {
        LottoGameMachine lottoGameMachine = lottoGameManageInitialize();
        LottoTickets lottoTickets = lottoGameMachine.makeLottos();
        WinningLotto winnings = lottoGameMachine.findWinnings();
        lottoGameMachine.lottoDraw(lottoTickets, winnings);
    }

    private LottoGameMachine lottoGameManageInitialize() {
        mainScreen.showInputMoney();
        int input = InputUtil.nextInt();
        Budget budget = Budget.amounts(input);
        LottoGameMachine lottoGameMachine = new LottoGameMachine(budget, lottoGameScreen);
        return lottoGameMachine;
    }
}
