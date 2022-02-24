package controller;

import domain.Lotto;
import service.LottoMachine;
import domain.LottoResult;
import domain.Lottos;
import domain.Payment;
import domain.WinningLotto;
import view.InputView;
import view.OutputView;

public class LottoController {
	private final LottoMachine lottoMachine;

	public LottoController(LottoMachine lottoMachine) {
		this.lottoMachine = lottoMachine;
	}

	public void run() {
		Payment payment = InputView.insertPayment();
		int lottoCount = payment.calculateLottoCount();
		OutputView.printLottoCount(lottoCount);
		Lottos lottos = lottoMachine.createLottos(lottoCount);
		OutputView.printLottos(lottos);
		Lotto lotto = InputView.insertLotto();
		WinningLotto winningLotto = InputView.insertBonus(lotto);
		LottoResult lottoResult = new LottoResult(lottos.calculateRank(winningLotto));
		OutputView.printRankCounts(lottoResult.countRank());
		double profitRate = payment.calculateProfitRate(lottoResult.calculateTotalProfit());
		OutputView.printProfitRate(profitRate);
	}
}
