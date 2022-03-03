package lotto.controller;

import java.util.ArrayList;
import java.util.List;

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
        OutputView.printLottoCount(manualLottoCount, autoLottoCount);
        return new Lottos(autoLottoCount, requestBuyManualLottoNumbers(manualLottoCount));
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

    private List<Lotto> requestBuyManualLottoNumbers(Count manualLottoCount) {
        List<Lotto> lottos = new ArrayList<>();
        OutputView.printRequestManualLottoNumberUI();
        int countValue = manualLottoCount.value();
        while (countValue-- > 0) {
            Lotto lotto = requestManualLottoNumber();
            lottos.add(lotto);
        }
        return lottos;
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
}
