package lotto;

import java.util.ArrayList;
import java.util.List;

import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.domain.factory.LottosFactory;
import com.woowacourse.lotto.exception.InvalidMoneyException;
import com.woowacourse.lotto.utils.StringSeparator;
import com.woowacourse.lotto.view.InputView;
import com.woowacourse.lotto.view.OutputView;
import jdk.internal.util.xml.impl.Input;

public class LottoAppMain {
	public static void main(String[] args) {
		Money money = getMoneyForPurchaseOfLotto();
		int countOfManualLotto = getCountOfManualLotto();
		Lottos lottos = getLottos(money, countOfManualLotto);

		OutputView.printCountOfPurchasedLotto(money.getCountOfLotto(), countOfManualLotto);
		OutputView.printLotto(lottos);

		LottoResult lottoResult = new LottoResult(getWinningLotto(), lottos);

		OutputView.printLottoMatchResult(lottoResult.matchLotto());
		OutputView.printEarningsRate(lottoResult);
	}

	private static Lottos getLottos(Money money, int countOfManualLotto) {
		try {
			return new LottosFactory(money, countOfManualLotto).generateLotto(inputManualLotto(countOfManualLotto));
		} catch (Exception e) {
			OutputView.printExceptionMessage(e.getMessage());
			return getLottos(money, getCountOfManualLotto());
		}
	}

	private static List<String> inputManualLotto(int count) {
		List<String> manualLotto = new ArrayList<>();
		InputView.printDemandManualLotto();
		while(count --> 0) {
			manualLotto.add(InputView.inputManualLottoNumber());
		}
		return manualLotto;
	}

	private static int getCountOfManualLotto() {
		try {
			return InputView.inputCountOfManualLotto();
		} catch (Exception e) {
			OutputView.printExceptionMessage(e.getMessage());
			return getCountOfManualLotto();
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
