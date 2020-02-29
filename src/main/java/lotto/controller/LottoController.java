package lotto.controller;

import static lotto.view.ConsoleInputView.*;
import static lotto.view.ConsoleOutputView.*;

import java.util.List;

import lotto.domain.LottoMachine;
import lotto.domain.lotto.CountOfManualLottoTicket;
import lotto.domain.lotto.Generator.ManualLottoTicketGenerator;
import lotto.domain.lotto.Lotto;
import lotto.domain.money.LottoPrice;
import lotto.domain.money.Money;
import lotto.domain.number.LottoNumber;
import lotto.domain.number.NumberLinesOfManualLotto;
import lotto.domain.result.LottoWinningResult;
import lotto.domain.result.WinningLotto;

public class LottoController {
	public void run() {
		Money inputMoney = new Money(inputMoney());
		int countOfAllLotto = LottoPrice.calculateCountOfLotto(inputMoney);
		CountOfManualLottoTicket countOfManualLottoTicket = new CountOfManualLottoTicket(inputCountOfManualLotto(),
			countOfAllLotto);
		NumberLinesOfManualLotto numberLinesOfManualLotto = receiveManualLotto(countOfManualLottoTicket);

		List<Lotto> lottoTicket = LottoMachine.buyLottoTicket(countOfAllLotto, numberLinesOfManualLotto);
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
		NumberLinesOfManualLotto numberLinesOfManualLotto = new NumberLinesOfManualLotto();
		numberLinesOfManualLotto.add(inputWinningLottoNumber());
		ManualLottoTicketGenerator manualLottoTicketGenerator = new ManualLottoTicketGenerator(
			numberLinesOfManualLotto);

		Lotto inputWinningLotto = manualLottoTicketGenerator.generate().iterator().next();
		LottoNumber inputBonusNumber = LottoNumber.valueOf(inputBonusLottoNumber());

		return new WinningLotto(inputWinningLotto, inputBonusNumber);
	}
}
