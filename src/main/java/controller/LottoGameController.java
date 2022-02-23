package controller;

import domain.AnswerLotto;
import domain.Lotto;
import domain.Lottos;
import domain.RandomLottoNumberGenerator;
import view.InputView;
import view.OutputView;

public class LottoGameController {

	public static void run() {
		Lottos lottos = Lottos.of(InputView.inputMoney(), new RandomLottoNumberGenerator());
		OutputView.printLottos(lottos);
		AnswerLotto answerLotto = new AnswerLotto(InputView.inputAnsNumbers(), InputView.inputBonusNumber());
	}
}
