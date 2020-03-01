package lotto.controller;

import java.util.List;

import lotto.domain.lottocount.AutoLottoCount;
import lotto.domain.lottocount.LottoCount;
import lotto.domain.lottocount.ManualLottoCount;
import lotto.domain.lottos.Lottos;
import lotto.domain.lottostore.LottoStore;
import lotto.domain.money.MoneyForLotto;
import lotto.domain.result.ResultStatistic;
import lotto.domain.result.WinningLotto;
import lotto.view.InputView;
import lotto.view.OutputView;

/**
 * 로또 실행을 담당, View와 Domain을 연결
 *
 * @author 토니, 히히
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/20
 */
public class LottoController {
	public void run() {
		MoneyForLotto moneyForLotto = new MoneyForLotto(InputView.getMoneyForLotto());
		String inputManualLottoCount = InputView.getManualLottoCount();
		LottoCount manualLottoCount = new ManualLottoCount(inputManualLottoCount, moneyForLotto.calculateAmountOfLottos());
		LottoCount autoLottoCount = new AutoLottoCount(inputManualLottoCount, moneyForLotto.calculateAmountOfLottos());
		List<String> inputManualLottoNumbers = InputView.getManualLottoNumbers(manualLottoCount.getLottoCount());

		LottoStore lottoStore = new LottoStore();
		Lottos purchasedLottos = lottoStore.purchase(autoLottoCount, inputManualLottoNumbers);
		OutputView.printPurchasedLottos(manualLottoCount, autoLottoCount, purchasedLottos);

		String inputWinningLottoNumbers = InputView.getWinningLotto();
		String inputBonusLottoNumber = InputView.getBonusLottoNumber();
		WinningLotto winningLotto = new WinningLotto(inputWinningLottoNumbers, inputBonusLottoNumber);

		ResultStatistic result = ResultStatistic.calculate(purchasedLottos, winningLotto);
		OutputView.printResultStatistic(result, moneyForLotto);
	}
}
