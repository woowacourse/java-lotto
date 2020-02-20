package lotto;

import java.util.List;

import lotto.controller.LottoController;
import lotto.domain.Lotto;
import lotto.domain.LottoMoney;
import lotto.exception.InvalidMoneyException;
import lotto.view.ConsoleInputView;
import lotto.view.ConsoleOutputView;

public class ConsoleUILottoApplication {
	public static void main(String[] args) {
		LottoMoney inputLottoMoney = receiveInputMoney();
		LottoController lottoController = new LottoController();

		int numberOfLotto = inputLottoMoney.purchaseLotto();
		ConsoleOutputView.printPurchaseCompleteMessage(numberOfLotto);
		List<Lotto> lottos = lottoController.start(numberOfLotto);
		ConsoleOutputView.printPurchasedLotto(lottos);
	}

	private static LottoMoney receiveInputMoney() {
		try {
			return new LottoMoney(ConsoleInputView.inputMoney());
		} catch (InvalidMoneyException ime) {
			ConsoleOutputView.printExceptionMessage(ime.getMessage());
			return receiveInputMoney();
		}
	}
}
