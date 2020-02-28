package lotto.controller;

import static lotto.view.ConsoleInputView.*;
import static lotto.view.ConsoleOutputView.*;

import lotto.domain.lotto.CountOfManualLotto;
import lotto.domain.lotto.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.lotto.LottoParser;
import lotto.domain.lotto.Lottos;
import lotto.domain.lottomoney.LottoMoney;
import lotto.domain.lottomoney.LottoPrice;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottonumber.NumberLinesOfManualLotto;
import lotto.domain.result.LottoWinningResult;
import lotto.domain.result.WinningLotto;

public class LottoController {
	public void run() {
		LottoMoney inputMoney = new LottoMoney(inputMoney());
		int countOfAllLotto = LottoPrice.calculateCountOfLotto(inputMoney);
		CountOfManualLotto countOfManualLotto = new CountOfManualLotto(inputCountOfManualLotto(), countOfAllLotto);
		NumberLinesOfManualLotto numberLinesOfManualLotto = receiveManualLotto(countOfManualLotto);

		LottoMachine lottoMachine = new LottoMachine();
		Lottos lottos = lottoMachine.buyLottoTicket(countOfAllLotto, countOfManualLotto, numberLinesOfManualLotto);
		printPurchaseCompleteMessage(countOfManualLotto, lottoMachine);
		printPurchasedLotto(lottos);

		LottoWinningResult winningResult = new LottoWinningResult(lottos, receiveWinningLotto());
		printWinningResult(winningResult.getLottoRankCount());
		printWinningRatio(winningResult.calculateWinningRatio(inputMoney));
	}

	private static NumberLinesOfManualLotto receiveManualLotto(CountOfManualLotto countOfManualLotto) {
		NumberLinesOfManualLotto numberLinesOfManualLotto = new NumberLinesOfManualLotto();
		while (countOfManualLotto.isNotZero()) {
			numberLinesOfManualLotto.add(inputManualLottoNumber());
		}
		return numberLinesOfManualLotto;
	}

	private static WinningLotto receiveWinningLotto() {
		Lotto inputWinningLotto = new Lotto(LottoParser.parser(inputWinningLottoNumber()));
		LottoNumber inputBonusNumber = LottoNumber.valueOf(inputBonusLottoNumber());
		return new WinningLotto(inputWinningLotto, inputBonusNumber);
	}
}
