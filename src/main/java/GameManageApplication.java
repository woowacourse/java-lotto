import domain.LottoGameMachine;
import domain.budget.Budget;
import util.InputUtil;
import util.OutputUtil;
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
        lottoGameMachine.gameStart();
    }

    private LottoGameMachine lottoGameManageInitialize() {
        mainScreen.showInputMoney();
        int input = InputUtil.nextInt();
        Budget budget = Budget.amounts(input);
        LottoGameMachine lottoGameMachine = new LottoGameMachine(budget);
        return lottoGameMachine;
    }
}
