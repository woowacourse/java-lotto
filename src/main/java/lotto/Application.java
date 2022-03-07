package lotto;

import java.util.List;
import lotto.controller.LottoController;
import lotto.dto.LottosResult;
import lotto.dto.StatisticsResult;
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
        LottosResult lottosResult = lottoController.buyLotto(inputMoney, manualAmount, manualLottoNumbers);
        OutputView.printLottosSize(lottosResult.getManualAmount(), lottosResult.getAutoLottoAmount());
        OutputView.printLottos(lottosResult.getLottos());

        // 당첨 확인
        List<Integer> winnerNumbers = InputView.inputWinnerNumbers();
        int bonusNumber = InputView.inputBonusNumber();
        StatisticsResult result = lottoController.match(lottosResult.getLottos(), winnerNumbers, bonusNumber, inputMoney);
        OutputView.printRanks(result);
    }
}
