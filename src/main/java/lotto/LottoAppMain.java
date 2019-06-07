package lotto;

import java.util.ArrayList;
import java.util.List;

import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.domain.factory.LottosFactory;
import com.woowacourse.lotto.view.InputView;
import com.woowacourse.lotto.view.OutputView;

public class LottoAppMain {
	public static void main(String[] args) {
		LottoMoney lottoMoney = InputView.inputMoneyForPurchaseOfLotto();
		int countOfManualLotto = InputView.inputCountOfManualLotto();

		LottosFactory lottosFactory = new LottosFactory(lottoMoney, countOfManualLotto);
		Lottos lottos = lottosFactory.generateLotto(inputManualLotto(countOfManualLotto));

		OutputView.printCountOfPurchasedLotto(lottoMoney.getCountOfLotto(), countOfManualLotto);
		OutputView.printLotto(lottos);

		LottoResult lottoResult = new LottoResult(InputView.inputWinningLotto(), lottos);

		OutputView.printLottoResult(lottoResult);
		OutputView.printEarningsRate(lottoMoney, lottoResult);
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
