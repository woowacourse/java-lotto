package lotto.controller;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lotto.domain.lottoMoney.InvalidLottoMoneyException;
import lotto.domain.lottoMoney.LottoMoney;
import lotto.domain.lottoMoney.PurchasingCount;
import lotto.domain.lottoNumber.InvalidLottoNumberException;
import lotto.domain.lottoNumber.LottoNumber;
import lotto.domain.lottoTicket.AutoLottoTicketsFactory;
import lotto.domain.lottoTicket.InvalidLottoTicketException;
import lotto.domain.lottoTicket.InvalidManualLottoTicketCountException;
import lotto.domain.lottoTicket.LottoTicket;
import lotto.domain.lottoTicket.LottoTickets;
import lotto.domain.lottoTicket.ManualLottoTicketCount;
import lotto.domain.lottoTicket.ManualLottoTicketsFactory;
import lotto.domain.result.InvalidWinningLottoException;
import lotto.domain.result.WinningLotto;
import lotto.domain.result.WinningResult;
import lotto.view.ConsoleInputView;
import lotto.view.ConsoleOutputView;

public class LottoController {
	public void play() {
		LottoMoney lottoMoney = receiveInputLottoMoney();
		LottoTickets lottoTickets = purchaseLottoTicketsFor(lottoMoney);
		ConsoleOutputView.printPurchasedLottoTickets(lottoTickets);

		WinningLotto winningLotto = generateWinningLotto();
		WinningResult winningResult = lottoTickets.produceWinningResultBy(winningLotto);
		ConsoleOutputView.printWinningLottoResult(winningResult);

		long winningRate = winningResult.calculateWinningRate(lottoMoney);
		ConsoleOutputView.printWinningRate(winningRate);
	}

	private static LottoMoney receiveInputLottoMoney() {
		try {
			String inputLottoMoney = ConsoleInputView.inputLottoMoney();
			return LottoMoney.valueOf(inputLottoMoney);
		} catch (InvalidLottoMoneyException e) {
			ConsoleOutputView.printException(e.getMessage());
			return receiveInputLottoMoney();
		}
	}

	private LottoTickets purchaseLottoTicketsFor(LottoMoney lottoMoney) {
		PurchasingCount purchasingCount = lottoMoney.generatePurchasingLottoTicketCount();
		ManualLottoTicketCount manualLottoTicketCount = receiveManualLottoTicketCount(purchasingCount);

		LottoTickets manualLottoTickets = purchaseManualLottoTicket(manualLottoTicketCount);
		purchasingCount.useFor(manualLottoTicketCount);

		LottoTickets autoLottoTickets = AutoLottoTicketsFactory.generate(purchasingCount);
		ConsoleOutputView.printPurchasedLottoTicketCount(manualLottoTickets, autoLottoTickets);
		return manualLottoTickets.concat(autoLottoTickets);
	}

	private ManualLottoTicketCount receiveManualLottoTicketCount(PurchasingCount purchasingCount) {
		try {
			String inputManualLottoTicketNumber = ConsoleInputView.inputManualLottoTicketNumber();
			return new ManualLottoTicketCount(inputManualLottoTicketNumber, purchasingCount);
		} catch (InvalidManualLottoTicketCountException e) {
			ConsoleOutputView.printException(e.getMessage());
			return receiveManualLottoTicketCount(purchasingCount);
		}
	}

	private LottoTickets purchaseManualLottoTicket(ManualLottoTicketCount manualLottoTicketCount) {
		try {
			ConsoleOutputView.printInputManualLottoTicket();

			return IntStream.range(0, manualLottoTicketCount.getManualLottoTicketCount())
				.mapToObj(i -> ConsoleInputView.inputLottoTicket())
				.collect(Collectors.collectingAndThen(Collectors.toList(), ManualLottoTicketsFactory::generate));
		} catch (InvalidLottoNumberException | InvalidLottoTicketException e) {
			ConsoleOutputView.printException(e.getMessage());
			return purchaseManualLottoTicket(manualLottoTicketCount);
		}
	}

	private WinningLotto generateWinningLotto() {
		try {
			LottoTicket winningLottoTicket = receiveInputWinningLottoTicket();
			LottoNumber bonusLottoNumber = receiveInputBonusLottoNumber();
			return new WinningLotto(winningLottoTicket, bonusLottoNumber);
		} catch (InvalidWinningLottoException e) {
			ConsoleOutputView.printException(e.getMessage());
			return generateWinningLotto();
		}
	}

	private static LottoTicket receiveInputWinningLottoTicket() {
		try {
			String inputWinningLottoNumbers = ConsoleInputView.inputWinningLottoNumbers();
			return LottoTicket.valueOf(inputWinningLottoNumbers);
		} catch (InvalidLottoTicketException | InvalidLottoNumberException e) {
			ConsoleOutputView.printException(e.getMessage());
			return receiveInputWinningLottoTicket();
		}
	}

	private static LottoNumber receiveInputBonusLottoNumber() {
		try {
			String inputBonusLottoNumber = ConsoleInputView.inputBonusLottoNumber();
			return LottoNumber.valueOf(inputBonusLottoNumber);
		} catch (InvalidLottoNumberException e) {
			ConsoleOutputView.printException(e.getMessage());
			return receiveInputBonusLottoNumber();
		}
	}
}
