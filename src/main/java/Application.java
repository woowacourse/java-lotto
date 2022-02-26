import static view.InputView.requestBonusNumber;
import static view.InputView.requestManualLottoCount;
import static view.InputView.requestTotalLottoPrice;
import static view.InputView.requestWinningNumbers;
import static view.OutputView.printLottoResults;
import static view.OutputView.printPrizePriceRatio;
import static view.OutputView.printPurchaseInfo;

import controller.LottoController;
import domain.LottoGame;
import domain.LottoReferee;
import domain.Lottos;

public class Application {

    private static final LottoController controller = new LottoController();

    public static void main(String[] args) {
        Lottos lottos = controller.initCustomerLottos(requestTotalLottoPrice(), requestManualLottoCount());
        printPurchaseInfo(lottos);

        LottoReferee referee = controller.initLottoReferee(requestWinningNumbers(), requestBonusNumber());

        LottoGame lottoGame = new LottoGame(lottos, referee);
        printLottoResults(lottoGame.getResultStatistics());
        printPrizePriceRatio(lottoGame.calculatePrizePriceRatio());
    }
}
