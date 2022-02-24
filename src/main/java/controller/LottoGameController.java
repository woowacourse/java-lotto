package controller;

import domain.AnswerLotto;
import domain.BonusNumber;
import domain.AnswerLottoNumbers;
import domain.Lottos;
import domain.RandomLottoNumberGenerator;

import static view.InputView.*;
import static view.OutputView.*;

public class LottoGameController {

	public static void run() {
		try {
			Lottos lottos = Lottos.of(inputMoney(), new RandomLottoNumberGenerator());
			printLottos(lottos);
			AnswerLotto answerLotto = initAnswerLotto();
			printStatistics(lottos.generateEachCount(answerLotto));
			printProfitRatio(lottos.generateProfitRatio(answerLotto));
		} catch (IllegalArgumentException e) {
			printErrorMessage(e.getMessage());
		}
	}

	private static AnswerLotto initAnswerLotto() {
		AnswerLottoNumbers lottoNumbers = new AnswerLottoNumbers(inputAnsNumbers());
		BonusNumber bonusNumber = new BonusNumber(inputBonusNumber());
		return new AnswerLotto(lottoNumbers, bonusNumber);
	}
}
