package lotto.controller;

import static lotto.view.ConsoleInputView.*;
import static lotto.view.ConsoleOutputView.*;

import java.util.List;

import lotto.domain.LottoMachine;
import lotto.domain.lotto.Lotto;
import lotto.domain.number.NumberLinesOfManualLotto;
import lotto.domain.result.LottoWinningResult;
import lotto.domain.result.WinningLotto;

public class LottoController {
	public void run() {
		LottoMachine lottoMachine = new LottoMachine(inputMoney(), inputCountOfManualLotto());
		List<Lotto> lottoTicket = lottoMachine.buyLottoTicket(receiveManualLottoNumber(lottoMachine));
		printPurchaseCompleteMessage(lottoMachine);
		printLottoTicket(lottoTicket);

		LottoWinningResult winningResult = new LottoWinningResult(lottoTicket, receiveWinningLotto());
		printWinningResult(winningResult.getLottoRankCount());
		printWinningRatio(winningResult.calculateWinningRatio(lottoMachine));
	}

	private NumberLinesOfManualLotto receiveManualLottoNumber(LottoMachine lottoMachine) {
		NumberLinesOfManualLotto numberLinesOfManualLotto = new NumberLinesOfManualLotto();
		while (lottoMachine.needMoreManualNumber()) {
			numberLinesOfManualLotto.add(inputManualLottoNumber());
		}
		return numberLinesOfManualLotto;
	}

	private WinningLotto receiveWinningLotto() {
		return new WinningLotto(inputWinningLottoNumber(), inputBonusLottoNumber());
	}
}
