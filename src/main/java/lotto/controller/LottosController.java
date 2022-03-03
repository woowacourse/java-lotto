package lotto.controller;

import lotto.domain.lotto.Count;
import lotto.domain.lotto.Money;
import lotto.domain.factory.LottoFactory;
import lotto.domain.factory.MoneyFactory;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottosController {

    public Lottos buyLottos() {
        Money money = requestMoney();
        Count totalLottoCount = money.countToBuyLotto();
        Count manualLottoCount = requestManualLottoCount(money);
        Count autoLottoCount = totalLottoCount.subtract(manualLottoCount);
        Lottos lottos = requestBuyLottos(manualLottoCount, autoLottoCount.value());
        OutputView.printLottoCount(manualLottoCount, autoLottoCount);
        OutputView.printLottos(lottos);
        return lottos;
    }

    private Money requestMoney() {
        try {
            String input = InputView.inputMoney();
            return MoneyFactory.valueOf(input);
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return requestMoney();
        }
    }

    private Count requestManualLottoCount(Money money) {
        try {
            String input = InputView.inputCountManualLotto();
            Count count = new Count(Integer.parseInt(input));
            money.validateBuyableLottoCount(count);
            return count;
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return requestManualLottoCount(money);
        }
    }

    private Lottos requestBuyLottos(Count manualLottoCount, int autoLottoCount) {
        Lottos lottos = new Lottos();
        requestBuyManualLottoNumbers(manualLottoCount, lottos);
        buyAutoLottos(autoLottoCount, lottos);
        return lottos;
    }

    private void requestBuyManualLottoNumbers(Count manualLottoCount, Lottos lottos) {
        OutputView.printRequestManualLottoNumberUI();
        int countValue = manualLottoCount.value();
        while (countValue-- > 0) {
            Lotto lotto = requestManualLottoNumber();
            lottos.add(lotto);
        }
    }

    private Lotto requestManualLottoNumber() {
        try {
            String text = InputView.inputManualLottoNumber();
            return LottoFactory.valueOf(text);
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return requestManualLottoNumber();
        }
    }

    private void buyAutoLottos(int autoLottoCount, Lottos lottos) {
        while (autoLottoCount-- > 0) {
            lottos.add(LottoFactory.auto());
        }
    }
}
