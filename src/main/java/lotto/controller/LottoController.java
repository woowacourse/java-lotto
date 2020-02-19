package lotto.controller;

import java.io.IOException;

import lotto.domain.LottoCount;
import lotto.utils.InputUtil;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
	public static void run() {
		LottoCount lc = new LottoCount(readMoney());
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
