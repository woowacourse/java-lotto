package lotto;

import java.util.List;

import lotto.controller.LottoController;
import lotto.domain.Lotto;
import lotto.domain.LottoMoney;
import lotto.exception.InvalidMoneyException;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ConsoleUILottoApplication {
	public static void main(String[] args) {
		LottoMoney inputLottoMoney = receiveInputMoney();
		LottoController lottoController = new LottoController();

		int numberOfLotto = inputLottoMoney.purchaseLotto();
		OutputView.printPurchaseCompleteMessage(numberOfLotto);
		List<Lotto> lottos = lottoController.start(numberOfLotto);
		OutputView.printPurchasedLotto(lottos);
	}

	private static LottoMoney receiveInputMoney() {
		try {
			return new LottoMoney(InputView.inputMoney());
		} catch (InvalidMoneyException ime) {
			OutputView.printExceptionMessage(ime.getMessage());
			return receiveInputMoney();
		}
	}
}
