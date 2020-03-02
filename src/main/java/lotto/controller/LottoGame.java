package lotto.controller;

import static lotto.view.InputView.inputBonusBall;
import static lotto.view.InputView.inputLottoMoney;
import static lotto.view.InputView.inputManualCount;
import static lotto.view.InputView.inputManualLottoTicketNumbers;
import static lotto.view.OutputView.printLottoPurchaseCount;
import static lotto.view.OutputView.printLottoTicket;
import static lotto.view.OutputView.printStatistics;
import static lotto.view.OutputView.printTotalProfits;

import java.util.List;

import lotto.domain.Lotto;
import lotto.domain.LottoGenerative;
import lotto.domain.LottoMachine;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.MatchResult;
import lotto.domain.PurchaseCount;
import lotto.domain.PurchaseMoney;
import lotto.domain.WinningLotto;
import lotto.view.InputView;

/**
 * 입출력과 도메인 사이의 중재 역할을 수행하는 컨트롤러 클래스
 *
 * @version 1.0.0
 * @author K.S.KIM
 * @since 2020/02/23
 */
public class LottoGame {
	public void run() {
		PurchaseMoney purchaseMoney = new PurchaseMoney(inputLottoMoney());
		PurchaseCount manualCount = new PurchaseCount(inputManualCount());
		PurchaseCount autoCount = manualCount.calculateRestPurchaseCount(purchaseMoney);
		printLottoPurchaseCount(manualCount, autoCount);

		LottoTicket lottoTicket = purchaseLottoTicket(purchaseMoney, manualCount);
		printLottoTicket(lottoTicket);

		WinningLotto winningLotto = inputWinningLotto();
		printMatchResult(lottoTicket, winningLotto);
	}

	private LottoTicket purchaseLottoTicket(PurchaseMoney purchaseMoney, PurchaseCount manualCount) {
		List<String> lottoTicketNumbers = inputManualLottoTicketNumbers(manualCount);
		LottoGenerative lottoMachine = new LottoMachine(lottoTicketNumbers);
		return lottoMachine.generate(purchaseMoney);
	}

	private WinningLotto inputWinningLotto() {
		Lotto lotto = Lotto.ofComma(InputView.inputWinningLotto());
		LottoNumber bonusNumber = LottoNumber.of(inputBonusBall());
		return new WinningLotto(lotto, bonusNumber);
	}

	private void printMatchResult(LottoTicket lottoTicket, WinningLotto winningLotto) {
		MatchResult matchResult = lottoTicket.matchAll(winningLotto);
		printStatistics(matchResult);
		printTotalProfits(matchResult.calculateTotalProfits());
	}
}
