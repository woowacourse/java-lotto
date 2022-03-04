package lotto;

import java.util.List;
import lotto.controller.LottoController;
import lotto.controller.MoneyController;
import lotto.domain.Lotto;
import lotto.domain.LottoWinningNumbers;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.dto.Result;

public class Application {

    public static void main(final String... args) {
        MoneyController moneyController = new MoneyController();
        LottoController lottoController = new LottoController();

        Money money = moneyController.inputMoney();
        List<Lotto> manualLottos = lottoController.inputManualLottos(money.purchasedLottoAmount());

        Lottos lottos = lottoController.createLottos(manualLottos, money.purchasedLottoAmount() - manualLottos.size());
        LottoWinningNumbers winningNumbers = lottoController.createLottoWinningNumbers();

        Result result = lottoController.calculateResult(winningNumbers, lottos);
        moneyController.printProfit(result, money);
    }
}
