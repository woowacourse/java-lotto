package controller;

import java.util.List;

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
		LottoTicket lottoTicket = new LottoTicket(createLottoTicket(money));

		Lotto winningNumber = Lotto.from(requestWinningLottoInput());
		Number bonusNumber = Number.from(requestBonusNumberInput());

		WinningNumbers winningNumbers = new WinningNumbers(winningNumber, bonusNumber);

		findWinningResult(lottoTicket, winningNumbers, money);
	}

	private String requestMoneyInput() {
		outputView.printRequestMoney();
		return inputView.requestMoney();
	}

	private List<Lotto> createLottoTicket(Money money) {
		int manualLottoCount = requestManualLottoCountInput();

		String[][] inputManualLotto = requestManualLottoInput(manualLottoCount);
		List<Lotto> lottoTicket = lottoFactory.generateLottoTicketAsManual(inputManualLotto);
		money.purchaseManualLotto(manualLottoCount);

		lottoTicket.addAll(lottoFactory.generateLottoTicketAsAuto(money));
		outputView.printPurchasedLottoTicket(manualLottoCount, lottoTicket);
		return lottoTicket;
	}

	private int requestManualLottoCountInput() {
		outputView.printRequestManualLottoCount();
		return inputView.requestManualLottoCount();
	}

	private String[][] requestManualLottoInput(int manualLottoCount) {
		outputView.printRequestManualLotto();
		return inputView.requestManualLotto(manualLottoCount);
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
