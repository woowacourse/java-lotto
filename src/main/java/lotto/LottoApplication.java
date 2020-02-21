package lotto;

import lotto.domain.Lotto;
import lotto.domain.LottoFactory;
import lotto.domain.LottoManager;
import lotto.domain.Money;
import lotto.domain.WinLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;

public class LottoApplication {
    public static void main(String[] args) {
        Money money = new Money(InputView.inputBuyMoney());
        List<Lotto> lotteries = LottoFactory.createLotteries(money);
        OutputView.printLotteris(lotteries);

        WinLotto winLotto = new WinLotto(InputView.inputWinNumber(), InputView.inputBounsBall());

        LottoManager lottoManager = new LottoManager(lotteries, winLotto);
        lottoManager.findHitLotto();
        OutputView.printResult(money, lottoManager);
    }
}
