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

        int inputMoney = InputView.inputMoney();
        int manualCount = InputView.inputManualLottoAmount();
        List<List<Integer>> manualLottos = InputView.inputManualLottoNumbers(manualCount);

        Lottos lottos = lottoController.buyLotto(inputMoney, manualCount, manualLottos);
        OutputView.printLottos(lottos.getLottos());

        List<Integer> winnerNumbers = InputView.inputWinnerNumbers();
        int bonusNumber = InputView.inputBonusNumber();

        Map<Rank, Integer> result = lottoController.match(lottos, winnerNumbers, bonusNumber);
        OutputView.printRanks(result);
        OutputView.printRate(lottoController.rate(result, inputMoney));

    }
}
