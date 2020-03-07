package lotto.controller;

import java.util.Arrays;
import java.util.List;

import lotto.domain.lottoNumber.LottoNumber;
import lotto.domain.lottoNumber.LottoNumberCache;
import lotto.domain.lottoTicket.LottoTicket;
import lotto.domain.lottoTicket.LottoTickets;
import lotto.domain.purchase.LottoMachine;
import lotto.domain.purchase.LottoMoney;
import lotto.domain.purchase.PurchasingCount;
import lotto.domain.result.WinningLotto;
import lotto.domain.result.WinningResult;
import lotto.domain.strategy.AutoLottoTicketsGenerator;
import lotto.domain.strategy.LottoTicketsGenerator;
import lotto.domain.strategy.ManualLottoTicketsGenerator;
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
		PurchasingCount totalPurchasingCount = new PurchasingCount(lottoMoney.calculatePurchasableCount());
		PurchasingCount manualPurchasingCount = PurchasingCount.valueOf(ConsoleInputView.inputManualPurchasingCount());
		PurchasingCount autoPurchasingCount = totalPurchasingCount.subtract(manualPurchasingCount);

		List<LottoTicketsGenerator> lottoTicketGenerators = generateLottoTicketGenerators(manualPurchasingCount);
		LottoTickets lottoTickets = purchaseLottoTicketsBy(totalPurchasingCount, lottoTicketGenerators);
		ConsoleOutputView.printTotalPurchasingCount(manualPurchasingCount, autoPurchasingCount);
		ConsoleOutputView.printPurchasedLottoTickets(lottoTickets);
		return lottoTickets;
	}

	private List<LottoTicketsGenerator> generateLottoTicketGenerators(PurchasingCount manualPurchasingCount) {
		ConsoleOutputView.printInputManualLottoTicket();
		List<String> inputManualLottoTickets = ConsoleInputView.inputManualLottoTickets(manualPurchasingCount);

		return Arrays.asList(new ManualLottoTicketsGenerator(inputManualLottoTickets),
			new AutoLottoTicketsGenerator(LottoNumberCache.values()));
	}

	private LottoTickets purchaseLottoTicketsBy(PurchasingCount totalPurchasingCount,
		List<LottoTicketsGenerator> manualPurchasingCount) {
		LottoMachine lottoMachine = new LottoMachine(manualPurchasingCount);
		return lottoMachine.purchaseLottoTickets(totalPurchasingCount);
	}

	private WinningLotto generateWinningLotto() {
		LottoTicket winningLottoTicket = LottoTicket.valueOf(ConsoleInputView.inputWinningLottoNumbers());
		LottoNumber bonusLottoNumber = LottoNumber.valueOf(ConsoleInputView.inputBonusLottoNumber());
		return new WinningLotto(winningLottoTicket, bonusLottoNumber);
	}

}
