package lotto;

import java.util.ArrayList;
import java.util.List;

import lotto.domain.Lotto;
import lotto.domain.LottoManager;
import lotto.domain.LottoResult;
import lotto.domain.ManualLottoCount;
import lotto.domain.Money;
import lotto.domain.WinLotto;
import lotto.domain.factory.AutomaticLottoFactory;
import lotto.domain.factory.ManualLottoFactory;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {

	public static void main(String[] args) {
		Money money = new Money(InputView.inputBuyMoney());
		ManualLottoCount manualLottoCount = new ManualLottoCount(InputView.inputManualLottoCount(), money);

		List<Lotto> lotteris = createLotteris(money, manualLottoCount);
		OutputView.printLotteris(lotteris);

		WinLotto winLotto = new WinLotto(InputView.inputWinNumber(), InputView.inputBonusBall());
		LottoManager lottoManager = new LottoManager(lotteris, winLotto);
		LottoResult lottoResult = new LottoResult(lottoManager.compareLotteries(), money);
		OutputView.printResult(lottoResult);
	}

	private static List<Lotto> createLotteris(Money money, ManualLottoCount manualLottoCount) {
		List<Lotto> manualLotteris = createManualLotteris(manualLottoCount);
		int automaticLotterisCount = money.calculateLottoCount(manualLotteris.size());
		List<Lotto> automaticLotteris = createAutomaticLotteris(automaticLotterisCount);
		OutputView.printLottoCount(manualLotteris.size(), automaticLotteris.size());

		manualLotteris.addAll(automaticLotteris);
		return manualLotteris;
	}

	private static List<Lotto> createManualLotteris(ManualLottoCount manualLottoCount) {
		InputView.printManualLotto();
		List<Lotto> lotteris = new ArrayList<>();
		while (manualLottoCount.hasNext()) {
			String inputLottoNumbers = InputView.input();
			lotteris.add(new ManualLottoFactory(inputLottoNumbers).create());
		}
		return lotteris;
	}

	private static List<Lotto> createAutomaticLotteris(int count) {
		List<Lotto> lotteris = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			lotteris.add(new AutomaticLottoFactory().create());
		}
		return lotteris;
	}
}
