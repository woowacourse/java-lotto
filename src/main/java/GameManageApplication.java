import domain.LottoGameMachine;
import domain.budget.Budget;
import util.InputUtil;

public class GameManageApplication {

    public void run() {
        LottoGameMachine lottoGameMachine = lottoGameManageInitialize();
        lottoGameMachine.on();
    }

    private LottoGameMachine lottoGameManageInitialize() {
        int input = InputUtil.nextInt();
        Budget budget = Budget.amounts(input);
        LottoGameMachine lottoGameMachine = new LottoGameMachine(budget);
        return lottoGameMachine;
    }
}
