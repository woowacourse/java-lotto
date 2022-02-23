package controller;

import domain.AnswerLotto;
import domain.Lottos;
import view.InputView;
import view.OutputView;

public class LottoGameController {

	public static void run() {
		Lottos lottos = new Lottos(InputView.inputMoney());
		OutputView.printLottos(lottos);
		AnswerLotto answerLotto = new AnswerLotto(InputView.inputAnsNumbers(), InputView.inputBonusNumber());
		System.out.println(answerLotto);
	}
}
