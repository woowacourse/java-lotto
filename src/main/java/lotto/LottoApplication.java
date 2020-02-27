package lotto;

import lotto.domain.result.TotalResult;
import lotto.domain.result.WinningLotto;
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

public class LottoApplication {
	public static void main(String[] args) {
		try {
			Money money = Money.valueOf(InputView.inputMoney());
			PurchaseLottoCount purchaseCount = createPurchaseCount(money);
			LottoTickets lottoTickets = createLottoTickets(purchaseCount);
			OutputView.printLottos(lottoTickets);
			WinningLotto winningLotto = createWinningLotto();
			TotalResult totalResult = new TotalResult(winningLotto.calculateResult(lottoTickets), money);
			OutputView.printStatistics(totalResult);
		} catch (RuntimeException ex) {
			OutputView.printExceptionMessage(ex.getMessage());
		}
	}

	private static PurchaseLottoCount createPurchaseCount(Money money) {
		LottoCount totalCount = money.calculatePurchaseCount();
		LottoCount manualCount = LottoCount.valueOf(InputView.inputManualTicketSize());
		return new PurchaseLottoCount(totalCount, manualCount);
	}

	private static LottoTickets createLottoTickets(PurchaseLottoCount purchaseCount) {
		LottoCount manualCount = purchaseCount.getManualCount();
		LottoTickets lottos = CompositeLottoTicketsFactory.of(
			new ManualLottoTicketsFactory(InputView.inputManualLotto(manualCount.getCount()), manualCount),
			new AutoLottoTicketsFactory(purchaseCount.calculateAutoCount())
		).create();
		OutputView.printLottoCount(purchaseCount);
		return lottos;
	}

	private static WinningLotto createWinningLotto() {
		LottoTicket winningLottoTicket = LottoTicket.of(InputView.inputWinningLotto());
		LottoBall bonusBall = LottoBall.valueOf(InputView.inputWinningBonusBall());
		return new WinningLotto(winningLottoTicket, bonusBall);
	}
}
