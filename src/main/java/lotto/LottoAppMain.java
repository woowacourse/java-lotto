package lotto;

import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.exception.InvalidMoneyException;
import com.woowacourse.lotto.utils.StringSeparator;
import com.woowacourse.lotto.view.InputView;
import com.woowacourse.lotto.view.OutputView;

public class LottoAppMain {
	public static void main(String[] args) {
		Money money = getMoneyForPurchaseOfLotto();
		LottoFactory lottoFactory = new LottoFactory(money);
		OutputView.printNumberOfLotto(money);
		Lottos lottos = lottoFactory.generateLotto();
		OutputView.printLotto(lottos);
		WinningLotto winningLotto = getWinningLotto();
		LottoResult lottoResult = new LottoResult(winningLotto, lottos);
		OutputView.printLottoMatchResult(lottoResult.matchLotto());
		OutputView.printEarningsRate(lottoResult);
	}

	public static Money getMoneyForPurchaseOfLotto() {
		try {
			return new Money(InputView.inputMoneyForPurchaseOfLotto());
		} catch (InvalidMoneyException e) {
			System.out.println(e.getMessage());
			return getMoneyForPurchaseOfLotto();
		}
	}

	public static WinningLotto getWinningLotto() {
		try {
			return new WinningLotto(StringSeparator.splitString(InputView.inputWinningLotto()), getBonusBall());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return getWinningLotto();
		}
	}

	public static BonusBall getBonusBall() {
		try {
			return new BonusBall(InputView.inputBonusBall());
		} catch (Exception e) {
			return getBonusBall();
		}
	}
}
