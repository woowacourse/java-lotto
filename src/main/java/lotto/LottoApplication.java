package lotto;

import java.util.List;

import lotto.domain.Lotto;
import lotto.domain.LottoFactory;
import lotto.domain.LottoManager;
import lotto.domain.Money;
import lotto.domain.WinLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
	public static void main(String[] args) throws CloneNotSupportedException {
		Money money = new Money(InputView.inputBuyMoney());
		List<Lotto> lotteris = LottoFactory.createLotteries(money);
		OutputView.printLotteris(lotteris);

		WinLotto winLotto = new WinLotto(InputView.inputWinNumber(), InputView.inputBonusBall());
		LottoManager lottoManager = new LottoManager(lotteris, winLotto);

		lottoManager.compareLotto();
		OutputView.printResult();
		OutputView.printIncomeRate(money);
	}

}
