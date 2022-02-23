package controller;

import domain.AnswerLotto;
import domain.BonusNumber;
import domain.AnswerLottoNumbers;
import domain.Lottos;
import domain.RandomLottoNumberGenerator;
import view.InputView;
import view.OutputView;

public class LottoGameController {

	public static void run() {
		try {
			Lottos lottos = Lottos.of(InputView.inputMoney(), new RandomLottoNumberGenerator());
			OutputView.printLottos(lottos);
			AnswerLotto answerLotto = initAnswerLotto();
			OutputView.printStatistics(lottos.generateEachCount(answerLotto));
			OutputView.printProfitRatio(lottos.generateProfitRatio(answerLotto));
		}
		catch (IllegalArgumentException e) {
			OutputView.printErrorMessage(e.getMessage());
		}
	}

	private static AnswerLotto initAnswerLotto() {
		AnswerLottoNumbers lottoNumbers = new AnswerLottoNumbers(InputView.inputAnsNumbers());
		BonusNumber bonusNumber = new BonusNumber(InputView.inputBonusNumber());
		return new AnswerLotto(lottoNumbers, bonusNumber);
	}
}
