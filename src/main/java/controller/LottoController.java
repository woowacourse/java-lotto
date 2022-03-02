package controller;

import java.util.List;
import java.util.stream.Collectors;

import domain.Lotto;
import domain.LottoFactory;
import domain.LottoTicket;
import domain.Money;
import domain.LottoNumber;
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
		final Money money = new Money(requestMoneyInput());
		LottoTicket lottoTicket = createLottoTicket(money);
		WinningNumbers winningNumbers = createWinningNumbers();
		findWinningResult(lottoTicket, winningNumbers, money);
	}

	private int requestMoneyInput() {
		outputView.printRequestMoney();
		return inputView.requestMoney();
	}

	private LottoTicket createLottoTicket(Money money) {
		int manualLottoCount = requestManualLottoCountInput();
		int autoLottoCount = money.findPurchaseLottoCount() - manualLottoCount;
		List<List<Integer>> inputManualLotto = requestManualLottoInput(manualLottoCount);

		List<Lotto> lottos = lottoFactory.generateLottos(autoLottoCount, inputManualLotto);
		outputView.printPurchasedLottoTicket(manualLottoCount, lottos);
		return new LottoTicket(lottos);
	}

	private int requestManualLottoCountInput() {
		outputView.printRequestManualLottoCount();
		return inputView.requestManualLottoCount();
	}

	private List<List<Integer>> requestManualLottoInput(int manualLottoCount) {
		outputView.printRequestManualLotto();
		return inputView.requestManualLotto(manualLottoCount);
	}

	private WinningNumbers createWinningNumbers() {
		List<LottoNumber> lottoNumbers = requestWinningLottoInput().stream()
			.map(LottoNumber::new)
			.collect(Collectors.toList());
		Lotto winningNumber = new Lotto(lottoNumbers);

		LottoNumber bonusLottoNumber = new LottoNumber(requestBonusNumberInput());
		return new WinningNumbers(winningNumber, bonusLottoNumber);
	}

	private List<Integer> requestWinningLottoInput() {
		outputView.printRequestWinningNumbers();
		return inputView.requestWinningNumbers();
	}

	private int requestBonusNumberInput() {
		outputView.printRequestBonusNumber();
		return inputView.requestBonusNumber();
	}

	private void findWinningResult(LottoTicket lottoTicket, WinningNumbers winningNumbers, Money money) {
		WinningResult winningResult = lottoTicket.findWinningResult(winningNumbers);
		outputView.printWinningResult(winningResult.getWinningResult());
		outputView.printRateOfProfit(winningResult.getRateOfProfit(money));
	}
}
