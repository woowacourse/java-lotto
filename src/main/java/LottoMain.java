import controller.LottoController;
import java.util.List;
import java.util.Map;
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
        controller.printGeneratedLottoTickets(lottoTickets);
        return lottoTickets;
    }

    private static void settingLottoGame(LottoTickets lottoTickets) {
        List<Integer> winningNumbers = controller.inputWinningNumbers();
        Integer bonusNumber = controller.inputBonusNumber();
        controller.initLottoGame(lottoTickets, winningNumbers, bonusNumber, new DefaultLottoWinningPrizeStrategy());
    }

    private static void printLottoGameResult() {
        Map<WinningPrize, Integer> winningResults = controller.winningResults();
        Double rateOfReturn = controller.rateOfReturn();
        controller.printWinningResults(winningResults);
        controller.printRateOfReturn(rateOfReturn);
    }
}
