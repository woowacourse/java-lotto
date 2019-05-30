package lotto;

import java.util.ArrayList;
import java.util.List;

import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.domain.factory.LottoFactoryController;
import com.woowacourse.lotto.exception.InvalidMoneyException;
import com.woowacourse.lotto.utils.StringSeparator;
import com.woowacourse.lotto.view.InputView;
import com.woowacourse.lotto.view.OutputView;

public class LottoAppMain {
	public static void main(String[] args) {
		Money money = getMoneyForPurchaseOfLotto();
		Lottos lottos = getLottos(money);

		OutputView.printNumberOfLotto(money);
		OutputView.printLotto(lottos);

		LottoResult lottoResult = new LottoResult(getWinningLotto(), lottos);

		OutputView.printLottoMatchResult(lottoResult.matchLotto());
		OutputView.printEarningsRate(lottoResult);
	}

	private static Lottos getLottos(Money money) {
		int countOfManualLotto = inputCountOfManualLotto();
		try {
			return new LottoFactoryController(money, countOfManualLotto).generateLotto(inputManualLotto(countOfManualLotto));
		} catch (Exception e) {
			OutputView.printExceptionMessage(e.getMessage());
			return getLottos(money);
		}
	}

	private static List<String> inputManualLotto(int count) {
		List<String> manualLotto = new ArrayList<>();
		while (count-- > 0) {
			manualLotto.add(InputView.inputManualLottoNumber());
		}
		return manualLotto;
	}

	private static int inputCountOfManualLotto() {
		try {
			return InputView.inputCountOfManualLotto();
		} catch (Exception e) {
			OutputView.printExceptionMessage(e.getMessage());
			return inputCountOfManualLotto();
		}
	}

	public static Money getMoneyForPurchaseOfLotto() {
		try {
			return new Money(InputView.inputMoneyForPurchaseOfLotto());
		} catch (InvalidMoneyException e) {
			OutputView.printExceptionMessage(e.getMessage());
			return getMoneyForPurchaseOfLotto();
		}
	}

	public static WinningLotto getWinningLotto() {
		try {
			return new WinningLotto(StringSeparator.splitString(InputView.inputWinningLotto()), getBonusBall());
		} catch (Exception e) {
			OutputView.printExceptionMessage(e.getMessage());
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
