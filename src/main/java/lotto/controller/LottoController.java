package lotto.controller;

import java.util.List;

import lotto.domain.lottoNumber.LottoNumber;
import lotto.domain.lottoTicket.LottoTicket;
import lotto.domain.lottoTicket.LottoTickets;
import lotto.domain.purchase.LottoMachine;
import lotto.domain.purchase.LottoMoney;
import lotto.domain.purchase.TotalPurchasingCount;
import lotto.domain.result.WinningLotto;
import lotto.domain.result.WinningResult;
import lotto.view.ConsoleInputView;
import lotto.view.ConsoleOutputView;

public class LottoController {

	public void play() {
		LottoMoney lottoMoney = LottoMoney.valueOf(ConsoleInputView.inputLottoMoney());
		LottoTickets lottoTickets = purchaseLottoTicketsFor(lottoMoney);

		WinningLotto winningLotto = generateWinningLotto();
		WinningResult winningResult = lottoTickets.produceWinningResultBy(winningLotto);
		ConsoleOutputView.printWinningLottoResult(winningResult);

		int winningRate = winningResult.calculateWinningRate(lottoMoney);
		ConsoleOutputView.printWinningRate(winningRate);
	}

	private LottoTickets purchaseLottoTicketsFor(LottoMoney lottoMoney) {
		TotalPurchasingCount totalPurchasingCount =
			TotalPurchasingCount.from(ConsoleInputView.inputManualPurchasingCount(), lottoMoney);
		LottoTickets lottoTickets = purchaseLottoTicketsBy(totalPurchasingCount);
		ConsoleOutputView.printTotalPurchasingCount(totalPurchasingCount);
		ConsoleOutputView.printPurchasedLottoTickets(lottoTickets);
		return lottoTickets;
	}

	private LottoTickets purchaseLottoTicketsBy(TotalPurchasingCount totalPurchasingCount) {
		ConsoleOutputView.printInputManualLottoTicket();
		List<String> inputManualLottoTickets = ConsoleInputView.inputManualLottoTickets(
			totalPurchasingCount.getManualPurchasingCount());
		LottoMachine lottoMachine = new LottoMachine(inputManualLottoTickets);
		return lottoMachine.purchaseLottoTickets(totalPurchasingCount);
	}

	private WinningLotto generateWinningLotto() {
		LottoTicket winningLottoTicket = LottoTicket.valueOf(ConsoleInputView.inputWinningLottoNumbers());
		LottoNumber bonusLottoNumber = LottoNumber.valueOf(ConsoleInputView.inputBonusLottoNumber());
		return new WinningLotto(winningLottoTicket, bonusLottoNumber);
	}

}
