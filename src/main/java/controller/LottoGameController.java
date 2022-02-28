package controller;

import domain.*;
import util.RandomLottoNumberGenerator;

import static view.InputView.*;
import static view.OutputView.*;

public class LottoGameController {

	public static void run() {
		try {
			Lottos lottos = initLottos();
			AnswerLotto answerLotto = initAnswerLotto();
			printResults(lottos.generateResult(answerLotto));
		} catch (IllegalArgumentException e) {
			printErrorMessage(e.getMessage());
		}
	}

	private static Lottos initLottos() {
		Lottos lottos = Lottos.of(new Money(inputMoney()), new RandomLottoNumberGenerator());
		printLottos(lottos);

		return lottos;
	}

	private static AnswerLotto initAnswerLotto() {
		AnswerLottoNumbers lottoNumbers = new AnswerLottoNumbers(inputAnsNumbers());
		BonusNumber bonusNumber = new BonusNumber(inputBonusNumber());

		return new AnswerLotto(lottoNumbers, bonusNumber);
	}

}
