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
		outputView.printRequestMoney();
		Money money = Money.from(inputView.requestMoney());

		LottoTicket lottoTicket = new LottoTicket(lottoFactory.generateLottoTicket(money));
		outputView.printPurchasedLottoTicket(lottoTicket.getLottoTicket());

		outputView.printRequestWinningNumbers();
		Lotto winningLotto = Lotto.from(inputView.requestWinningNumbers());

		outputView.printRequestBonusNumber();
		Number bonusNumber = Number.from(inputView.requestBonusNumber());

		WinningNumbers winningNumbers = new WinningNumbers(winningLotto, bonusNumber);

		WinningResult winningResult = WinningResult.createWinningResult(lottoTicket, winningNumbers);
		outputView.printWinningResult(winningResult.getWinningResult());

		outputView.printRateOfProfit(winningResult.getRateOfProfit(money));
	}
}
