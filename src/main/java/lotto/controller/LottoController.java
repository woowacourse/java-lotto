package lotto.controller;

import java.io.IOException;

import lotto.domain.LottoMachine;
import lotto.domain.LottoUser;
import lotto.domain.Lottos;
import lotto.dto.LottoCountDto;
import lotto.utils.InputUtil;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
	public static void run() {
		Lottos lottos = buyLottos();
		LottoUser lottoUser = new LottoUser(lottos);

		OutputView.printLottos(lottos.makeLottoDtos());
		// WinningNumber wn = new WinningNumber(readWinningNumber());
	}

	private static Lottos buyLottos() {
		LottoMachine lottoMachine = new LottoMachine();
		LottoCountDto lottoCountDto = new LottoCountDto(readMoney());

		OutputView.printLottoCount(lottoCountDto.getLottoCount());

		return new Lottos(lottoMachine.makeRandomLottos(lottoCountDto.getLottoCount()));
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
