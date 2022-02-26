package controller;

import domain.LottoResult;
import domain.Lottos;
import domain.Payment;
import domain.WinningLotto;
import view.InputConvertor;
import view.OutputView;

public class LottoController {

	public void run() {
		Payment payment = InputConvertor.createPayment();
		int lottoCount = payment.calculateLottoCount();
		OutputView.printLottoCount(lottoCount);

		Lottos lottos = InputConvertor.createLottos(lottoCount);
		OutputView.printLottos(lottos);

		WinningLotto winningLotto = InputConvertor.createWinningLotto();

		OutputView.printLottoResult(new LottoResult(lottos.countRank(winningLotto)), payment);
	}
}
