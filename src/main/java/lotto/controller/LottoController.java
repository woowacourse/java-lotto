package lotto.controller;

import lotto.domain.result.WinningLotto;
import lotto.domain.result.WinningResult;
import lotto.domain.ticket.AutoLottoTicketsFactory;
import lotto.domain.ticket.CompositeLottoTicketsFactory;
import lotto.domain.ticket.LottoBall;
import lotto.domain.ticket.LottoCount;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTickets;
import lotto.domain.ticket.ManualLottoTicketsFactory;
import lotto.domain.ticket.Money;
import lotto.domain.ticket.PurchaseLottoCount;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
	public void run() {
		Money money = Money.valueOf(InputView.inputMoney());
		LottoTickets lottoTickets = createLottoTickets(money);
		OutputView.printPurchaseLottoTickets(lottoTickets);
		WinningLotto winningLotto = createWinningLotto();
		WinningResult winningResult = winningLotto.calculateResult(lottoTickets);
		OutputView.printStatistics(winningResult);
	}

	private LottoTickets createLottoTickets(Money money) {
		LottoCount totalCount = money.calculatePurchaseCount();
		LottoCount manualCount = LottoCount.valueOf(InputView.inputManualTicketSize());
		PurchaseLottoCount purchaseCount = new PurchaseLottoCount(totalCount, manualCount);
		CompositeLottoTicketsFactory compositeLottoFactory = new CompositeLottoTicketsFactory(
			new ManualLottoTicketsFactory(InputView.inputManualLottos(manualCount.getCount())),
			new AutoLottoTicketsFactory(purchaseCount.calculateAutoCount())
		);
		OutputView.printPurchaseLottoInfo(purchaseCount);
		return compositeLottoFactory.create();
	}

	private WinningLotto createWinningLotto() {
		LottoTicket winningLottoTicket = LottoTicket.of(InputView.inputWinningLotto2());
		LottoBall bonusBall = LottoBall.valueOf(InputView.inputWinningBonusBall());
		return new WinningLotto(winningLottoTicket, bonusBall);
	}
}
