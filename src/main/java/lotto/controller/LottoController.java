package lotto.controller;

import java.io.IOException;

import lotto.utils.InputUtil;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
	public static void run() {
		readMoney();
		//LottoCount lc = new LottoCount(readLottocount());
		// LottoGame lottoGame = new LottoGame(new LottoCount());
		//
		// WinningNumber wn = new WinningNumber(readWinningNumber());
	}

	public static int readMoney() {
		try {
			InputView.printInsertMoney();
			return InputUtil.readMoney();
		} catch (NumberFormatException | IOException e) {
			OutputView.printWrongMoneyInput();
			return readMoney();
		}
	}
}
