package controller;

import domain.Lotto;
import domain.LottoMachine;
import domain.LottoResult;
import domain.Lottos;
import domain.Payment;
import domain.WinningLotto;
import view.InputView;
import view.OutputView;

/**
 * 구입금액을 입력해 주세요.
 * 14000
 * 2개를 구매했습니다.
 * [8, 21, 23, 41, 42, 43]
 * [3, 5, 11, 16, 32, 38]
 * 지난 주 당첨 번호를 입력해 주세요.
 * 1, 2, 3, 4, 5, 6
 * 보너스 볼을 입력해 주세요.
 * 당첨 통계
 * ---------
 * 3개 일치 (5000원)- 1개
 * 4개 일치 (50000원)- 0개
 * 5개 일치 (1500000원)- 0개
 * 5개 일치, 보너스 볼 일치(30000000원) - 0개
 * 6개 일치 (2000000000원)- 0개
 * 총 수익률은 0.35입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)
 */

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
