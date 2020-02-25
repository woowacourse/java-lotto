package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoFactory;
import lotto.domain.LottoManager;
import lotto.domain.Money;
import lotto.domain.WinLotto;
import lotto.util.StringUtils;
import lotto.validator.InputValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoApplication {
    public static void main(String[] args) {
        Money money = new Money(InputView.inputBuyMoney());
        int manualCount = StringUtils.ToInt(InputView.inputManualCount());
        String manualLotto = setManualLotto(manualCount, money);

        List<Lotto> lotteries = LottoFactory.createLotteries(money, manualLotto);
        OutputView.printLotteries(lotteries, manualCount);

        WinLotto winLotto = setWinLotto();
        LottoManager lottoManager = new LottoManager(lotteries, winLotto);
        lottoManager.checkLotto();
        OutputView.printResult(money, lottoManager);
    }

    private static WinLotto setWinLotto() {
        String[] inputWinNumbers = StringUtils.splitNumber(InputView.inputWinNumber());
        return new WinLotto(inputWinNumbers, InputView.inputBonusBall());
    }

    private static String setManualLotto(int manualCount, Money money) {
        InputValidator.validateManualCount(manualCount, money);
        String manualLotto = "";
        if (manualCount > 0) {
            manualLotto = InputView.inputManualLotto(manualCount);
        }
        return manualLotto;
    }
}
