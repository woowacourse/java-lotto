package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoFactory;
import lotto.domain.LottoManager;
import lotto.domain.Money;
import lotto.domain.WinLotto;
import lotto.util.StringUtils;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoApplication {
    public static void main(String[] args) {
        Money money = new Money(InputView.inputBuyMoney());
        int manualCount = StringUtils.ToInt(InputView.inputManualCount());
        String manualLotto = "";
        if (manualCount > 0) {
            manualLotto = InputView.inputManualLotto(manualCount);
        }

        List<Lotto> lotteries = LottoFactory.createLotteries(money, manualLotto);
        OutputView.printLotteries(lotteries, manualCount);

        String[] inputWinNumbers = StringUtils.splitNumber(InputView.inputWinNumber());
        WinLotto winLotto = new WinLotto(inputWinNumbers, InputView.inputBonusBall());

        LottoManager lottoManager = new LottoManager(lotteries, winLotto);
        lottoManager.checkLotto();
        OutputView.printResult(money, lottoManager);
    }
}
