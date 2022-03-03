package lotto.controller;

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
        int manualLottoCount = requestManualLottoCount(money);
        int autoLottoCount = money.countToBuyLotto();
        Lottos lottos = requestBuyLottos(manualLottoCount, autoLottoCount);
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

    private int requestManualLottoCount(Money money) {
        try {
            String input = InputView.inputCountManualLotto();
            int count = Integer.parseInt(input);
            money.pay(Lotto.PRICE, count);
            return count;
        } catch (IllegalArgumentException exception) {
            OutputView.printException(exception);
            return requestManualLottoCount(money);
        }
    }

    private Lottos requestBuyLottos(int manualLottoCount, int autoLottoCount) {
        Lottos lottos = new Lottos();
        requestBuyManualLottoNumbers(manualLottoCount, lottos);
        buyAutoLottos(autoLottoCount, lottos);
        return lottos;
    }

    private void requestBuyManualLottoNumbers(int manualLottoCount, Lottos lottos) {
        OutputView.printRequestManualLottoNumberUI();
        while (manualLottoCount-- > 0) {
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
