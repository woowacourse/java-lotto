package lotto;

import java.util.List;
import java.util.Map;
import lotto.controller.LottoController;
import lotto.service.LottoService;
import lotto.domain.Lottos;
import lotto.domain.Rank;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {

    public static void main(String[] args) {
        LottoController lottoController = new LottoController(new LottoService());

        // 로또 구입
        int inputMoney = InputView.inputMoney();
        int manualAmount = InputView.inputManualLottoAmount();
        List<List<Integer>> manualLottoNumbers = InputView.inputManualLottoNumbers(manualAmount);
        Lottos lottos = lottoController.buyLotto(inputMoney, manualAmount, manualLottoNumbers);
        OutputView.printLottos(lottos.getLottos());

        // 당첨 확인
        List<Integer> winnerNumbers = InputView.inputWinnerNumbers();
        int bonusNumber = InputView.inputBonusNumber();
        Map<Rank, Integer> result = lottoController.match(lottos, winnerNumbers, bonusNumber);
        OutputView.printRanks(result);
        double profitRate = lottoController.getProfitRate(result, inputMoney);
        OutputView.printRate(profitRate);

    }
}
