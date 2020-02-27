package lotto.controller;

import static lotto.view.InputView.*;
import static lotto.view.OutputView.*;

import java.util.List;
import java.util.Random;

import lotto.domain.AutoLottoTicketFactory;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoPurchaseMoney;
import lotto.domain.LottoTicket;
import lotto.domain.ManualLottoTicketFactory;
import lotto.domain.MatchResult;
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
		LottoPurchaseMoney lottoPurchaseMoney = new LottoPurchaseMoney(inputLottoMoney());
		LottoTicket manualLottoTicket = purchaseManualLottoTicket(lottoPurchaseMoney);
		LottoTicket autoLottoTicket = purchaseAutoLottoTicket(lottoPurchaseMoney);
		printLottoTicket(manualLottoTicket, autoLottoTicket);

		LottoTicket lottoTicket = LottoTicket.concat(manualLottoTicket, autoLottoTicket);
		WinningLotto winningLotto = generateWinningLotto();
		printMatchResult(lottoTicket, winningLotto);
	}

	private LottoTicket purchaseManualLottoTicket(LottoPurchaseMoney lottoPurchaseMoney) {
		int manualCount = inputManualCount();
		List<String> lottoTicketNumbers = inputManualLottoTicketNumbers(manualCount);
		ManualLottoTicketFactory manualLottoTicketFactory = new ManualLottoTicketFactory(lottoTicketNumbers);
		return manualLottoTicketFactory.generate(lottoPurchaseMoney);
	}

	private LottoTicket purchaseAutoLottoTicket(LottoPurchaseMoney lottoPurchaseMoney) {
		Random random = new Random();
		AutoLottoTicketFactory autoLottoTicketFactory = new AutoLottoTicketFactory(random);
		return autoLottoTicketFactory.generate(lottoPurchaseMoney);
	}

	private WinningLotto generateWinningLotto() {
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
