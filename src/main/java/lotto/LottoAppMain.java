package lotto;

import java.util.ArrayList;
import java.util.List;

import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.domain.factory.LottosFactory;
import com.woowacourse.lotto.view.InputView;
import com.woowacourse.lotto.view.OutputView;

public class LottoAppMain {
	public static void main(String[] args) {
		Money money = InputView.inputMoneyForPurchaseOfLotto();
		int countOfManualLotto = InputView.inputCountOfManualLotto();

		LottosFactory lottosFactory = new LottosFactory(money, countOfManualLotto);
		Lottos lottos = lottosFactory.generateLotto(inputManualLotto(countOfManualLotto));

		OutputView.printCountOfPurchasedLotto(money.getCountOfLotto(), countOfManualLotto);
		OutputView.printLotto(lottos);

		LottoResult lottoResult = new LottoResult(InputView.inputWinningLotto(), lottos);

		OutputView.printLottoMatchResult(lottoResult.getLottoResult());
		OutputView.printEarningsRate(money, lottoResult);
	}

	private static List<String> inputManualLotto(int count) {
		List<String> manualLotto = new ArrayList<>();
		InputView.printDemandManualLotto();
		while (count-- > 0) {
			manualLotto.add(InputView.inputManualLottoNumber());
		}
		return manualLotto;
	}
}
