package lotto.controller;

import static lotto.view.InputView.*;
import static lotto.view.OutputView.*;

import java.util.List;

import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.MatchResult;
import lotto.domain.PurchaseInformation;
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
		PurchaseInformation purchaseInformation = inputPurchaseInformation();
		LottoTicket lottoTicket = purchaseLottoTicket(purchaseInformation);
		printLottoTicket(purchaseInformation, lottoTicket);

		WinningLotto winningLotto = inputWinningLotto();
		printMatchResult(lottoTicket, winningLotto);
	}

	private PurchaseInformation inputPurchaseInformation() {
		PurchaseMoney purchaseMoney = new PurchaseMoney(inputLottoMoney());
		int manualCount = inputManualCount();
		return new PurchaseInformation(purchaseMoney, manualCount);
	}

	private LottoTicket purchaseLottoTicket(PurchaseInformation purchaseInformation) {
		List<String> lottoTicketNumbers = inputManualLottoTicketNumbers(purchaseInformation.getManualCount());
		LottoMachine lottoMachine = new LottoMachine(lottoTicketNumbers);
		return lottoMachine.generate(purchaseInformation);
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
