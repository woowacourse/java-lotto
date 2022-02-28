package controller;

import domain.*;
import util.RandomLottoNumberGenerator;

import static view.InputView.*;
import static view.OutputView.*;

public class LottoGameController {

	public static void run() {
		try {
			LottoTickets lottoTickets = LottoTickets.of(new Money(inputMoney()), new RandomLottoNumberGenerator());
			printLottoTickets(lottoTickets);
			AnswerLotto answerLotto = AnswerLotto.of(inputAnsNumbers(), inputBonusNumber());
			printResults(lottoTickets.generateResult(answerLotto));
		} catch (IllegalArgumentException e) {
			printErrorMessage(e.getMessage());
		}
	}

}
