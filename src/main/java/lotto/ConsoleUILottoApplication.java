package lotto;

import java.util.List;

import lotto.controller.LottoController;
import lotto.domain.InvalidLottoMoneyException;
import lotto.domain.InvalidWinningLottoException;
import lotto.domain.Lotto;
import lotto.domain.LottoMoney;
import lotto.domain.LottoNumber;
import lotto.domain.WinningLotto;
import lotto.domain.WinningLottoParser;
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

		WinningLotto winningLotto = receiveWinningLotto();
		ConsoleOutputView.printStatisticsMessage();
	}

	private static WinningLotto receiveWinningLotto() {
		try {
			String inputWinningLottoNumber = ConsoleInputView.inputWinningLottoNumber();
			String inputBonusLottoNumber = ConsoleInputView.inputBonusLottoNumber();
			return new WinningLotto(new Lotto(WinningLottoParser.parser(inputWinningLottoNumber)),
				LottoNumber.valueOf(inputBonusLottoNumber));
		} catch (InvalidWinningLottoException iwle) {
			ConsoleOutputView.printExceptionMessage(iwle.getMessage());
			return receiveWinningLotto();
		}
	}

	private static LottoMoney receiveInputMoney() {
		try {
			return new LottoMoney(ConsoleInputView.inputMoney());
		} catch (InvalidLottoMoneyException ime) {
			ConsoleOutputView.printExceptionMessage(ime.getMessage());
			return receiveInputMoney();
		}
	}
}
