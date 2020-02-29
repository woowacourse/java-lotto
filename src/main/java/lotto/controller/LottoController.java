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
		PurchaseLottoCount purchaseCount = createPurchaseCount(money);
		LottoTickets lottoTickets = createLottoTickets(purchaseCount);
		OutputView.printLottoCount(purchaseCount);
		OutputView.printLottos(lottoTickets);
		WinningLotto winningLotto = createWinningLotto();
		WinningResult winningResult = winningLotto.calculateResult(lottoTickets);
		OutputView.printStatistics(winningResult);
	}

	private PurchaseLottoCount createPurchaseCount(Money money) {
		LottoCount totalCount = money.calculatePurchaseCount();
		LottoCount manualCount = LottoCount.valueOf(InputView.inputManualTicketSize());
		return new PurchaseLottoCount(totalCount, manualCount);
	}

	private LottoTickets createLottoTickets(PurchaseLottoCount purchaseCount) {
		LottoCount manualCount = purchaseCount.getManualCount();
		CompositeLottoTicketsFactory compositeLottoFactory = new CompositeLottoTicketsFactory(
			new ManualLottoTicketsFactory(InputView.inputManualLotto(manualCount.getCount())),
			new AutoLottoTicketsFactory(purchaseCount.calculateAutoCount())
		);
		return compositeLottoFactory.create();
	}

	private WinningLotto createWinningLotto() {
		LottoTicket winningLottoTicket = LottoTicket.of(InputView.inputWinningLotto());
		LottoBall bonusBall = LottoBall.valueOf(InputView.inputWinningBonusBall());
		return new WinningLotto(winningLottoTicket, bonusBall);
	}
}
