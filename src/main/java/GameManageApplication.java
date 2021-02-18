import domain.LottoGameMachine;
import domain.budget.Budget;
import domain.lotto.Lottos;
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
        Lottos lottos = lottoGameMachine.makeLottos();
        WinningLotto winnings = lottoGameMachine.findWinnings();
        lottoGameMachine.lottoDraw(lottos, winnings);
    }

    private LottoGameMachine lottoGameManageInitialize() {
        mainScreen.showInputMoney();
        int input = InputUtil.nextInt();
        Budget budget = Budget.amounts(input);
        LottoGameMachine lottoGameMachine = new LottoGameMachine(budget, lottoGameScreen);
        return lottoGameMachine;
    }
}
