package controller;

import java.util.Map;

import domain.Lotto;
import domain.LottoNumber;
import domain.Rank;
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
		Payment payment = createPayment();
		Lottos lottos = createLottos(payment);
		WinningLotto winningLotto = createWinningLotto();
		LottoResult lottoResult = createLottoResult(lottos.countRank(winningLotto));
		calculateTotalProfitRate(payment, lottoResult.calculateTotalProfit());
	}

	private Payment createPayment() {
		try {
			return new Payment(InputView.insertPayment());
		} catch (Exception e) {
			OutputView.printErrorMessage(e.getMessage());
			return createPayment();
		}
	}

	private Lottos createLottos(Payment payment) {
		int lottoCount = payment.calculateLottoCount();
		OutputView.printLottoCount(lottoCount);
		Lottos lottos = lottoMachine.createLottos(lottoCount);
		OutputView.printLottos(lottos);
		return lottos;
	}

	private WinningLotto createWinningLotto() {
		return new WinningLotto(createLotto(), createBonusNumber());
	}

	private Lotto createLotto() {
		try {
			return Lotto.of(InputView.insertLotto());
		} catch (Exception e) {
			OutputView.printErrorMessage(e.getMessage());
			return createLotto();
		}
	}

	private LottoNumber createBonusNumber() {
		try {
			return new LottoNumber(InputView.insertBonus());
		} catch (Exception e) {
			OutputView.printErrorMessage(e.getMessage());
			return createBonusNumber();
		}
	}

	private LottoResult createLottoResult(Map<Rank, Integer> ranks) {
		LottoResult lottoResult = new LottoResult(ranks);
		OutputView.printRankCounts(lottoResult);
		return lottoResult;
	}

	private void calculateTotalProfitRate(Payment payment, int totalProfit) {
		double profitRate = payment.calculateProfitRate(totalProfit);
		OutputView.printProfitRate(profitRate);
	}
}
