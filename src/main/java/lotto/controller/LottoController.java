package lotto.controller;

import static lotto.view.ConsoleInputView.*;
import static lotto.view.ConsoleOutputView.*;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottosGenerator;
import lotto.domain.lotto.LottoParser;
import lotto.domain.lotto.Lottos;
import lotto.domain.lottomoney.InvalidLottoMoneyException;
import lotto.domain.lottomoney.LottoMoney;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.result.LottoWinningResult;
import lotto.domain.result.WinningLotto;

public class LottoController {
	public void run() {
		LottoMoney inputLottoMoney = receiveInputMoney();
		int numberOfLotto = inputLottoMoney.calculateNumberOfLotto();
		printPurchaseCompleteMessage(numberOfLotto);

		Lottos lottos = LottosGenerator.generateLottos(numberOfLotto);
		printPurchasedLotto(lottos);

		Lotto inputWinningLotto = new Lotto(LottoParser.parser(inputWinningLottoNumber()));
		LottoNumber inputBonusNumber = LottoNumber.valueOf(inputBonusLottoNumber());
		WinningLotto winningLotto = new WinningLotto(inputWinningLotto, inputBonusNumber);

		LottoWinningResult winningResult = new LottoWinningResult(lottos, winningLotto);
		printStatisticsMessage();
		printWinningResult(winningResult.getLottoRankCount());

		int winningRatio = winningResult.calculateWinningRatio(inputLottoMoney);
		printWinningRatio(winningRatio);
	}

	private static LottoMoney receiveInputMoney() {
		try {
			return new LottoMoney(inputMoney());
		} catch (InvalidLottoMoneyException ime) {
			printExceptionMessage(ime.getMessage());
			return receiveInputMoney();
		}
	}
}
