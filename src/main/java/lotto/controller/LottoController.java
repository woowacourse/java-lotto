package lotto.controller;

import java.io.IOException;

import lotto.domain.LottoMachine;
import lotto.domain.Lottos;
import lotto.dto.LottoCountDto;
import lotto.utils.InputUtil;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
	public static void run() {
		LottoMachine lottoMachine = new LottoMachine();
		LottoCountDto lottoCountDto = new LottoCountDto(readMoney());
		Lottos lottos = new Lottos(lottoMachine.makeRandomLottos(lottoCountDto.getLottoCount()));

		// LottoGame lottoGame = new LottoGame(new LottoCountDto(readMoney()).getLottoCount());
		OutputView.printLottoCount(lottoCountDto.getLottoCount());
		OutputView.printLottos(lottos.makeLottoDtos());
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
