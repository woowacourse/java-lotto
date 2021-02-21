import domain.LottoGameMachine;
import domain.bettingMoney.BettingMoney;
import domain.lotto.LottoTickets;
import domain.lotto.WinningLotto;
import util.InputUtil;
import view.LottoGameScreen;
import view.MainScreen;

public class GameManageApplication {
    private final MainScreen mainScreen;
    private final LottoGameScreen lottoGameScreen;

    public GameManageApplication(final MainScreen mainScreen, final LottoGameScreen lottoGameScreen) {
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
        BettingMoney bettingMoney = BettingMoney.of(input);
        LottoGameMachine lottoGameMachine = new LottoGameMachine(bettingMoney, lottoGameScreen);
        return lottoGameMachine;
    }
}
