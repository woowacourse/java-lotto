package lotto;

import lotto.controller.LottoController;

public class ConsoleUILottoApplication {
	public static void main(String[] args) {
		LottoController lottoController = new LottoController();
		lottoController.run();
	}
}