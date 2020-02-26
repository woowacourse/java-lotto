package lotto.controller;

import lotto.domain.lottoMoney.InvalidLottoMoneyException;
import lotto.domain.lottoMoney.LottoMoney;
import lotto.domain.lottoNumber.InvalidLottoNumberException;
import lotto.domain.lottoNumber.LottoNumber;
import lotto.domain.lottoTicket.AutoLottoTicketsFactory;
import lotto.domain.lottoTicket.InvalidLottoTicketException;
import lotto.domain.lottoTicket.InvalidManualLottoTicketException;
import lotto.domain.lottoTicket.LottoTicket;
import lotto.domain.lottoTicket.LottoTickets;
import lotto.domain.lottoTicket.ManualLottoTicketNumber;
import lotto.domain.result.InvalidWinningLottoException;
import lotto.domain.result.WinningLotto;
import lotto.domain.result.WinningResult;
import lotto.view.ConsoleInputView;
import lotto.view.ConsoleOutputView;

public class LottoController {
	public void play() {
		LottoMoney lottoMoney = receiveInputLottoMoney();
		ManualLottoTicketNumber manualLottoTicketNumber = receiveManualLottoTicketNumber(lottoMoney);
		
		LottoTickets lottoTickets = purchaseLottoTicket(lottoMoney);
		ConsoleOutputView.printPurchasedLottoTickets(lottoTickets);

		WinningLotto winningLotto = generateWinningLotto();
		WinningResult winningResult = lottoTickets.produceWinningResultBy(winningLotto);
		ConsoleOutputView.printWinningLottoResult(winningResult);

		long winningRate = winningResult.calculateWinningRate(lottoMoney);
		ConsoleOutputView.printWinningRate(winningRate);
	}

	private ManualLottoTicketNumber receiveManualLottoTicketNumber(LottoMoney lottoMoney) {
		try {
			String inputManualLottoTicketNumber = ConsoleInputView.inputManualLottoTicketNumber();
			return new ManualLottoTicketNumber(inputManualLottoTicketNumber, lottoMoney);
		} catch (InvalidManualLottoTicketException e) {
			ConsoleOutputView.printException(e.getMessage());
			return receiveManualLottoTicketNumber(lottoMoney);
		}
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

	public LottoTickets purchaseLottoTicket(LottoMoney numberOfLotto) {
		long purchasableLottoTicketNumber = numberOfLotto.calculatePurchasableLottoTicket();
		ConsoleOutputView.printNumberOfPurchasedLottoTicket(purchasableLottoTicketNumber);
		return AutoLottoTicketsFactory.generate(purchasableLottoTicketNumber);
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
