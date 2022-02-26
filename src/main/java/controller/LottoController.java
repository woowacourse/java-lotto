package controller;

import domain.Lotto;
import domain.LottoFactory;
import domain.LottoTicket;
import domain.Money;
import domain.Number;
import domain.WinningNumbers;
import domain.WinningResult;
import view.InputView;
import view.OutputView;

public class LottoController {

	private final InputView inputView;
	private final OutputView outputView;
	private final LottoFactory lottoFactory;

	public LottoController(InputView inputView, OutputView outputView, LottoFactory lottoFactory) {
		this.inputView = inputView;
		this.outputView = outputView;
		this.lottoFactory = lottoFactory;
	}

	public void run() {
		final Money money = Money.from(requestMoneyInput());

		LottoTicket lottoTicket = new LottoTicket(lottoFactory.generateLottoTicket(money));
		outputView.printPurchasedLottoTicket(lottoTicket.getLottoTicket());

		Lotto winningNumber = Lotto.from(requestWinningLottoInput());
		Number bonusNumber = Number.from(requestBonusNumberInput());
		WinningNumbers winningNumbers = new WinningNumbers(winningNumber, bonusNumber);

		findWinningResult(lottoTicket, winningNumbers, money);
	}

	private String requestMoneyInput() {
		outputView.printRequestMoney();
		return inputView.requestMoney();
	}

	private String[] requestWinningLottoInput() {
		outputView.printRequestWinningNumbers();
		return inputView.requestWinningNumbers();
	}

	private String requestBonusNumberInput() {
		outputView.printRequestBonusNumber();
		return inputView.requestBonusNumber();
	}

	private void findWinningResult(LottoTicket lottoTicket, WinningNumbers winningNumbers, Money money) {
		WinningResult winningResult = new WinningResult(lottoTicket.findWinningResult(winningNumbers));
		outputView.printWinningResult(winningResult.getWinningResult());
		outputView.printRateOfProfit(winningResult.getRateOfProfit(money));
	}
}
