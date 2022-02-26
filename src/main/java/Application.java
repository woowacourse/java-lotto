import static view.InputView.requestBonusNumber;
import static view.InputView.requestManualsCount;
import static view.InputView.requestManualsNumbers;
import static view.InputView.requestTotalPrice;
import static view.InputView.requestWinningNumbers;
import static view.OutputView.printLottoResults;
import static view.OutputView.printPrizePriceRatio;
import static view.OutputView.printPurchaseInfo;

import controller.LottoController;
import domain.LottoCountsDto;
import domain.LottoGame;
import domain.LottoReferee;
import domain.Lottos;
import java.util.List;

public class Application {

    private static final LottoController controller = new LottoController();

    public static void main(String[] args) {
        LottoCountsDto countsDto = controller.initCountsDto(requestTotalPrice(), requestManualsCount());
        List<String> manualsNumbers = requestManualsNumbers(countsDto.getManuals());

        Lottos lottos = controller.initLottos(manualsNumbers, countsDto);
        printPurchaseInfo(lottos);

        LottoReferee referee = controller.initLottoReferee(requestWinningNumbers(), requestBonusNumber());
        LottoGame lottoGame = new LottoGame(lottos, referee);
        printLottoResults(lottoGame.getResultStatistics());
        printPrizePriceRatio(lottoGame.calculatePrizePriceRatio());
    }
}
