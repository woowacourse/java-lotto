package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;



public class ConsoleLottoApplication {
	public static void main(String[] args) {
		final Money inputMoney = new Money(InputView.inputPayment());
		PurchasingAmount purchasingAmount = new PurchasingAmount(inputMoney.getLottoMoneyValue());
		LottoTicketFactory lottoTicketFactory = new LottoTicketFactory();
		LottoTickets lottoTickets = lottoTicketFactory.buyLottoTicket(purchasingAmount);

		final WinningNumbers winningNumbers =
			new WinningNumbers(InputView.inputWinningNumbers(), InputView.inputBonusNumber());

		final Ranks results = new Ranks(lottoTickets, winningNumbers);

		OutputView.printResult(results);
		OutputView.printProfit(ProfitCalculator.calculate(inputMoney, results.getRanks()));
	}


}
