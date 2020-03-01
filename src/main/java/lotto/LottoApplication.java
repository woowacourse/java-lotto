package lotto;

import lotto.controller.LottoController;
import lotto.view.OutputView;

public class LottoApplication {
	public static void main(String[] args) {
		try {
			LottoController lottoController = new LottoController();
			lottoController.run();
		} catch (RuntimeException e) {
			OutputView.printExceptionMessage(e.getMessage());
		}
	}
}
