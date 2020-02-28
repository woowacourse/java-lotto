package lotto.controller;

import static lotto.view.ConsoleInputView.*;
import static lotto.view.ConsoleOutputView.*;

import lotto.domain.lotto.CountOfManualLottoTicket;
import lotto.domain.lotto.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.lotto.LottoParser;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.money.Money;
import lotto.domain.money.LottoPrice;
import lotto.domain.number.LottoNumber;
import lotto.domain.number.NumberLinesOfManualLotto;
import lotto.domain.result.LottoWinningResult;
import lotto.domain.result.WinningLotto;

public class LottoController {
	public void run() {
		Money inputMoney = new Money(inputMoney());
		int countOfAllLotto = LottoPrice.calculateCountOfLotto(inputMoney);
		CountOfManualLottoTicket countOfManualLottoTicket = new CountOfManualLottoTicket(inputCountOfManualLotto(), countOfAllLotto);
		NumberLinesOfManualLotto numberLinesOfManualLotto = receiveManualLotto(countOfManualLottoTicket);

		LottoTicket lottoTicket = LottoMachine.buyLottoTicket(countOfAllLotto, numberLinesOfManualLotto);
		printPurchaseCompleteMessage(countOfManualLottoTicket);
		printPurchasedLotto(lottoTicket);

		LottoWinningResult winningResult = new LottoWinningResult(lottoTicket, receiveWinningLotto());
		printWinningResult(winningResult.getLottoRankCount());
		printWinningRatio(winningResult.calculateWinningRatio(inputMoney));
	}

	private static NumberLinesOfManualLotto receiveManualLotto(CountOfManualLottoTicket countOfManualLottoTicket) {
		NumberLinesOfManualLotto numberLinesOfManualLotto = new NumberLinesOfManualLotto();
		while (countOfManualLottoTicket.isNotZero()) {
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
