package lotto;

import java.util.List;
import lotto.controller.LottoController;
import lotto.dto.request.LottoRequest;
import lotto.dto.request.StatisticsRequest;
import lotto.dto.result.LottosResult;
import lotto.service.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

    public static void main(String[] args) {
        LottoController lottoController = new LottoController(new LottoService());

        // 로또 구입
        int inputMoney = InputView.inputMoney();
        int manualAmount = InputView.inputManualLottoAmount();
        List<List<Integer>> manualLottoNumbers = InputView.inputManualLottoNumbers(manualAmount);
        LottoRequest lottoRequest = new LottoRequest(inputMoney, manualLottoNumbers);
        LottosResult lottosResult = lottoController.buyLotto(lottoRequest);
        OutputView.printLottos(lottosResult);

        // 당첨 확인
        List<Integer> winnerNumbers = InputView.inputWinnerNumbers();
        int bonusNumber = InputView.inputBonusNumber();
        StatisticsRequest statisticsRequest =
            new StatisticsRequest(lottosResult.getLottos(), winnerNumbers, bonusNumber, inputMoney);
        OutputView.printRanks(lottoController.match(statisticsRequest));
    }
}
