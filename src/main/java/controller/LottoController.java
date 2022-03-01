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
		LottoTicket lottoTicket = createLottoTicket(money);
		WinningNumbers winningNumbers = createWinningNumbers();
		findWinningResult(lottoTicket, winningNumbers, money);
	}

	private String requestMoneyInput() {
		outputView.printRequestMoney();
		return inputView.requestMoney();
	}

	private LottoTicket createLottoTicket(Money money) {
		int manualLottoCount = requestManualLottoCountInput();
		Money AutoPurchaseMoney = new Money((money.findPurchaseLottoCount() - manualLottoCount) * Money.LOTTO_PRICE);

		String[][] inputManualLotto = requestManualLottoInput(manualLottoCount);
		List<Lotto> lottos = lottoFactory.generateLottosAsManual(inputManualLotto);
		lottos.addAll(lottoFactory.generateLottosAsAuto(AutoPurchaseMoney));

		outputView.printPurchasedLottoTicket(manualLottoCount, lottos);
		return new LottoTicket(lottos);
	}

	private int requestManualLottoCountInput() {
		outputView.printRequestManualLottoCount();
		return inputView.requestManualLottoCount();
	}

	private String[][] requestManualLottoInput(int manualLottoCount) {
		outputView.printRequestManualLotto();
		return inputView.requestManualLotto(manualLottoCount);
	}

	private WinningNumbers createWinningNumbers() {
		Lotto winningNumber = Lotto.from(requestWinningLottoInput());
		Number bonusNumber = Number.from(requestBonusNumberInput());
		return new WinningNumbers(winningNumber, bonusNumber);
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
		WinningResult winningResult = lottoTicket.findWinningResult(winningNumbers);
		outputView.printWinningResult(winningResult.getWinningResult());
		outputView.printRateOfProfit(winningResult.getRateOfProfit(money));
	}
}
