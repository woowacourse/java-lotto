import controller.LottoController;
import java.util.List;
import java.util.Map;
import model.LottoNumberGenerateStrategy;
import model.LottoTickets;
import model.WinningPrize;

public class LottoMain {
    public static void main(String[] args) {
        LottoController lottoController = new LottoController();

        int purchaseMoney = lottoController.getPurchaseMoney();
        LottoTickets lottoTickets = lottoController
                .createLottoTickets(purchaseMoney, new LottoNumberGenerateStrategy());
        lottoController.printGeneratedLottoTickets(lottoTickets);

        List<Integer> winningNumbers = lottoController.getWinningNumbers();
        Integer bonusNumber = lottoController.getBonusNumber();
        lottoController.initLottoGame(lottoTickets, winningNumbers, bonusNumber);

        Map<WinningPrize, Integer> winningResults = lottoController.winningResults();
        Double rateOfReturn = lottoController.rateOfReturn();
        lottoController.printWinningResults(winningResults);
        lottoController.printRateOfReturn(rateOfReturn);
    }
}
