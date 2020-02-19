package lotto;

import lotto.controller.LottoController;
import lotto.domain.InvalidMoneyException;
import lotto.domain.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ConsoleUILottoApplication {
	public static void main(String[] args) {
		Money inputMoney = receiveInputMoney();
		LottoController lottoController = new LottoController();

		OutputView.printPurchaseCompleteMessage(inputMoney.purchaseLotto());
		lottoController.start(inputMoney);
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
