package lotto.controller;

import lotto.domain.result.LottoRanks;
import lotto.domain.result.WinningLotto;
import lotto.domain.result.WinningResult;
import lotto.domain.ticket.AutoLottoTicketsFactory;
import lotto.domain.ticket.LottoTicketsCompositor;
import lotto.domain.ticket.LottoBall;
import lotto.domain.ticket.LottoCount;
import lotto.domain.ticket.LottoTicket;
import lotto.domain.ticket.LottoTickets;
import lotto.domain.ticket.ManualLottoTicketsFactory;
import lotto.domain.ticket.Money;
import lotto.domain.ticket.PurchaseLottoCounts;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
	public void run() {
		Money money = Money.valueOf(InputView.inputMoney());
		LottoTickets lottoTickets = createLottoTickets(money);
		OutputView.printPurchaseLottoTickets(lottoTickets);
		WinningLotto winningLotto = createWinningLotto();
		LottoRanks lottoRanks = lottoTickets.matchLottoRanks(winningLotto);
		WinningResult winningResult = lottoRanks.calculateWinningResult();
		OutputView.printStatistics(winningResult);
	}

	private LottoTickets createLottoTickets(Money money) {
		LottoCount totalCount = money.calculatePurchaseCount();
		LottoCount manualCount = LottoCount.valueOf(InputView.inputManualTicketSize());
		PurchaseLottoCounts purchaseCounts = new PurchaseLottoCounts(totalCount, manualCount);
		LottoTicketsCompositor lottosCompositor = new LottoTicketsCompositor(
			new ManualLottoTicketsFactory(InputView.inputManualLottos(manualCount.getCount())),
			new AutoLottoTicketsFactory(purchaseCounts.calculateAutoCount())
		);
		OutputView.printPurchaseLottoInfo(purchaseCounts);
		return lottosCompositor.composite();
	}

	private WinningLotto createWinningLotto() {
		LottoTicket winningLottoTicket = LottoTicket.of(InputView.inputWinningLotto2());
		LottoBall bonusBall = LottoBall.valueOf(InputView.inputWinningBonusBall());
		return new WinningLotto(winningLottoTicket, bonusBall);
	}
}
