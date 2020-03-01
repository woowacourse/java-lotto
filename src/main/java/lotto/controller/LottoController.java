package lotto.controller;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.stream.IntStream;

import lotto.domain.lottoNumber.LottoNumber;
import lotto.domain.lottoTicket.LottoTicket;
import lotto.domain.lottoTicket.LottoTickets;
import lotto.domain.purchase.LottoMachine;
import lotto.domain.purchase.LottoMoney;
import lotto.domain.purchase.ManualLottoTicketCount;
import lotto.domain.purchase.PurchasingCount;
import lotto.domain.result.WinningLotto;
import lotto.domain.result.WinningResult;
import lotto.view.ConsoleInputView;
import lotto.view.ConsoleOutputView;

public class LottoController {

	public void play() {
		LottoMoney lottoMoney = LottoMoney.valueOf(ConsoleInputView.inputLottoMoney());
		LottoTickets lottoTickets = purchaseLottoTicketsFor(lottoMoney);
		ConsoleOutputView.printPurchasedLottoTickets(lottoTickets);

		WinningLotto winningLotto = generateWinningLotto();
		WinningResult winningResult = lottoTickets.produceWinningResultBy(winningLotto);
		ConsoleOutputView.printWinningLottoResult(winningResult);

		int winningRate = winningResult.calculateWinningRate(lottoMoney);
		ConsoleOutputView.printWinningRate(winningRate);
	}

	private LottoTickets purchaseLottoTicketsFor(LottoMoney lottoMoney) {
		PurchasingCount purchasingCount = lottoMoney.generatePurchasingLottoTicketCount();
		ManualLottoTicketCount manualLottoTicketCount = new ManualLottoTicketCount(
			ConsoleInputView.inputManualLottoTicketNumber(), purchasingCount);
		LottoMachine lottoMachine = new LottoMachine(purchasingCount, manualLottoTicketCount);
		return purchaseLottoTickets(lottoMachine, manualLottoTicketCount);
	}

	private LottoTickets purchaseLottoTickets(LottoMachine lottoMachine,
		ManualLottoTicketCount manualLottoTicketCount) {
		ConsoleOutputView.printInputManualLottoTicket();
		List<String> inputManualLottoTickets = receiveInputManualLottoTickets(manualLottoTicketCount);

		ConsoleOutputView.printPurchasedLottoTicketCount(lottoMachine.getNumberOfManualAndAutoLottoTickets());
		return lottoMachine.purchaseLottoTicketsManualAndAutoBy(inputManualLottoTickets);
	}

	private List<String> receiveInputManualLottoTickets(ManualLottoTicketCount manualLottoTicketCount) {
		return IntStream.range(0, manualLottoTicketCount.getManualLottoTicketCount())
			.mapToObj(i -> ConsoleInputView.inputLottoTicket())
			.collect(toList());
	}

	private WinningLotto generateWinningLotto() {
		LottoTicket winningLottoTicket = LottoTicket.valueOf(ConsoleInputView.inputWinningLottoNumbers());
		LottoNumber bonusLottoNumber = LottoNumber.valueOf(ConsoleInputView.inputBonusLottoNumber());
		return new WinningLotto(winningLottoTicket, bonusLottoNumber);
	}

}
