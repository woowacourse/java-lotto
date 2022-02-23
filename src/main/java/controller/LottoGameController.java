package controller;

import domain.AnswerLotto;
import domain.Lottos;
import domain.RandomLottoNumberGenerator;
import view.InputView;
import view.OutputView;

public class LottoGameController {

	public static void run() {
		try {
			Lottos lottos = Lottos.of(InputView.inputMoney(), new RandomLottoNumberGenerator());
			OutputView.printLottos(lottos);
			AnswerLotto answerLotto = new AnswerLotto(InputView.inputAnsNumbers(), InputView.inputBonusNumber());
			OutputView.printStatistics(lottos.generateEachCount(answerLotto));
			OutputView.printProfitRatio(lottos.generateProfitRatio(answerLotto));
		}
		catch (IllegalArgumentException e) {
			OutputView.printErrorMessage(e.getMessage());
		}
	}
}
