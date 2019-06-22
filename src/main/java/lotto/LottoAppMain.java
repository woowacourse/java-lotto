package lotto;

import java.util.ArrayList;
import java.util.List;

import com.woowacourse.lotto.domain.*;
import com.woowacourse.lotto.domain.dto.LottoRankDTO;
import com.woowacourse.lotto.domain.factory.LottosFactory;
import com.woowacourse.lotto.view.InputViewConsole;
import com.woowacourse.lotto.view.OutputViewConsole;

public class LottoAppMain {
	public static void main(String[] args) {
		LottoMoney lottoMoney = InputViewConsole.inputMoneyForPurchaseOfLotto();
		int countOfManualLotto = InputViewConsole.inputCountOfManualLotto();

		LottosFactory lottosFactory = new LottosFactory(lottoMoney, countOfManualLotto);
		Lottos lottos = lottosFactory.generateLotto(inputManualLotto(countOfManualLotto));

		OutputViewConsole.printCountOfPurchasedLotto(lottoMoney.getCountOfLotto(), countOfManualLotto);
		OutputViewConsole.printLotto(lottos);

		LottoResult lottoResult = new LottoResult(InputViewConsole.inputWinningLotto(), lottos);

		OutputViewConsole.printLottoResult(new LottoRankDTO(lottoResult.getRankResult()));
		OutputViewConsole.printEarningsRate(lottoMoney, lottoResult);
	}

	private static List<String> inputManualLotto(int count) {
		List<String> manualLotto = new ArrayList<>();
		InputViewConsole.printDemandManualLotto();
		while (count-- > 0) {
			manualLotto.add(InputViewConsole.inputManualLottoNumber());
		}
		return manualLotto;
	}
}
