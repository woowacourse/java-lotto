package controller;

import domain.Lotto;
import domain.LottoMachine;
import domain.LottoResult;
import domain.Lottos;
import domain.Payment;
import domain.WinningLotto;
import view.InputView;
import view.OutputView;

public class LottoController {
	private final InputView inputView = new InputView();

	public void run() {
		Payment payment = inputView.insertPayment();
		int lottoCount = payment.calculateLottoCount();
		OutputView.printLottoCount(lottoCount);
		Lottos lottos = new LottoMachine().createLottos(lottoCount);
		OutputView.printLottos(lottos);
		Lotto lotto = inputView.insertLotto();
		WinningLotto winningLotto = inputView.insertBonus(lotto);
		LottoResult lottoResult = new LottoResult(lottos.calculateRank(winningLotto));
		OutputView.printRankCounts(lottoResult.countRank());
		double profitRate = payment.calculateProfitRate(lottoResult.calculateTotalProfit());
		OutputView.printProfitRate(profitRate);
	}
}
