package lotto;

import com.woowacourse.lotto.domain.LottoFactory;
import com.woowacourse.lotto.domain.Money;
import com.woowacourse.lotto.exception.InvalidMoneyException;
import com.woowacourse.lotto.view.InputView;
import com.woowacourse.lotto.view.OutputView;

public class LottoAppMain {
	public static void main(String[] args) {
		Money money = getMoneyForPurchaseOfLotto();
		LottoFactory lottoFactory = new LottoFactory(money);
		OutputView.printNumberOfLotto(money);
		OutputView.printLotto(lottoFactory.generateLotto());
		InputView.inputWinningLotto();
	}

	public static Money getMoneyForPurchaseOfLotto() {
		try {
			return new Money(InputView.inputMoneyForPurchaseOfLotto());
		} catch (InvalidMoneyException e) {
			System.out.println(e.getMessage());
			return getMoneyForPurchaseOfLotto();
		}
	}
}
