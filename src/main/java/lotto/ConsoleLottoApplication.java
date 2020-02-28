package lotto;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;


public class ConsoleLottoApplication {
	public static void main(String[] args) {
		Money inputMoney = new Money(InputView.inputPayment());
		PurchasingAmount purchasingAmount = new PurchasingAmount(inputMoney.getLottoMoneyValue());
		List<LottoTicket> lottoTickets = new ArrayList<>();
		LottoTicketFactory.buyLottoTickets(purchasingAmount, lottoTickets);
		OutputView.printLottoState(lottoTickets);

		WinningNumbers winningNumbers =
			new WinningNumbers(InputView.inputWinningNumbers(), InputView.inputBonusNumber());

		Ranks results = new Ranks(new LottoTickets(lottoTickets), winningNumbers);

		OutputView.printResult(results);
		OutputView.printProfit(ProfitCalculator.calculate(inputMoney, results.getRanks()));
	}


}
