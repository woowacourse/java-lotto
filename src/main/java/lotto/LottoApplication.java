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
        List<Lotto> lotteries = LottoFactory.createLotteries(money);
        OutputView.printLotteries(lotteries);

        String[] inputWinNumbers = StringUtils.split(InputView.inputWinNumber());
        WinLotto winLotto = new WinLotto(inputWinNumbers, InputView.inputBonusBall());

        LottoManager lottoManager = new LottoManager(lotteries, winLotto);
        lottoManager.checkLotto();
        OutputView.printResult(money, lottoManager);
    }
}
