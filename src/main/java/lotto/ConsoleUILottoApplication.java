package lotto;

import lotto.controller.LottoController;
import lotto.domain.InvalidMoneyException;
import lotto.view.InputView;
import lotto.view.OutputView;

public class ConsoleUILottoApplication {
	public static void main(String[] args) {
		String inputMoney = receiveInputMoney();

		LottoController lottoController = new LottoController();
		lottoController.start(inputMoney);
	}

	private static String receiveInputMoney() {
		try {
			return InputView.inputMoney();
		} catch (InvalidMoneyException ime) {
			OutputView.printExceptionMessage(ime.getMessage());
			return receiveInputMoney();
		}
	}
}
