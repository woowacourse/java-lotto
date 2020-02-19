package lotto;

import java.util.List;

import lotto.controller.LottoController;
import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.exception.InvalidMoneyException;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ConsoleUILottoApplication {
	public static void main(String[] args) {
		Money inputMoney = receiveInputMoney();
		LottoController lottoController = new LottoController();

		int numberOfLotto = inputMoney.purchaseLotto();
		OutputView.printPurchaseCompleteMessage(numberOfLotto);
		List<Lotto> lottos = lottoController.start(numberOfLotto);
		OutputView.printPurchasedLotto(lottos);
	}

	private static Money receiveInputMoney() {
		try {
			return new Money(InputView.inputMoney());
		} catch (InvalidMoneyException ime) {
			OutputView.printExceptionMessage(ime.getMessage());
			return receiveInputMoney();
		}
	}
}
