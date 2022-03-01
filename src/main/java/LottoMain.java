import controller.LottoController;
import java.util.Map;
import java.util.Set;
import model.LottoNumberGenerateStrategy;
import model.LottoTickets;
import model.DefaultLottoWinningPrizeStrategy;
import model.WinningPrize;

public class LottoMain {
    public static LottoController controller = new LottoController();

    public static void main(String[] args) {
        LottoTickets lottoTickets = purchaseLottoTickets();
        settingLottoGame(lottoTickets);
        printLottoGameResult();
    }

    private static LottoTickets purchaseLottoTickets() {
        int purchaseMoney = controller.inputPurchaseMoney();
        LottoTickets lottoTickets = controller.createLottoTickets(purchaseMoney, new LottoNumberGenerateStrategy());
        controller.showGeneratedLottoTickets(lottoTickets);
        return lottoTickets;
    }

    private static void settingLottoGame(LottoTickets lottoTickets) {
        Set<Integer> winningNumbers = controller.inputWinningNumbers();
        int bonusNumber = controller.inputBonusNumber();
        controller.initLottoGame(lottoTickets, winningNumbers, bonusNumber, new DefaultLottoWinningPrizeStrategy());
    }

    private static void printLottoGameResult() {
        Map<WinningPrize, Integer> winningResults = controller.winningResults();
        Double rateOfReturn = controller.rateOfReturn();
        controller.showWinningResults(winningResults);
        controller.showRateOfReturn(rateOfReturn);
    }
}
