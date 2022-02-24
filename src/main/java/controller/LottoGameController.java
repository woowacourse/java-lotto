package controller;

import domain.AnswerLotto;
import domain.BonusNumber;
import domain.AnswerLottoNumbers;
import domain.Lottos;
import util.RandomLottoNumberGenerator;

import static view.InputView.*;
import static view.OutputView.*;

public class LottoGameController {

	public static void run() {
		try {
			Lottos lottos = initLottos();
			AnswerLotto answerLotto = initAnswerLotto();
			printResult(lottos, answerLotto);
		} catch (IllegalArgumentException e) {
			printErrorMessage(e.getMessage());
		}
	}

	private static Lottos initLottos() {
		Lottos lottos = Lottos.of(inputMoney(), new RandomLottoNumberGenerator());
		printLottos(lottos);

		return lottos;
	}

	private static AnswerLotto initAnswerLotto() {
		AnswerLottoNumbers lottoNumbers = new AnswerLottoNumbers(inputAnsNumbers());
		BonusNumber bonusNumber = new BonusNumber(inputBonusNumber());

		return new AnswerLotto(lottoNumbers, bonusNumber);
	}

	private static void printResult(Lottos lottos, AnswerLotto answerLotto) {
		printStatistics(lottos.generateEachCount(answerLotto));
		printProfitRatio(lottos.generateProfitRatio(answerLotto));
	}
}
