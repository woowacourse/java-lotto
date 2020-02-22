package lotto;

import lotto.domain.LottoBall;
import lotto.domain.LottoCount;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.LottoTicketsGenerator;
import lotto.domain.Money;
import lotto.domain.RandomLottoTicketGenerator;
import lotto.domain.TotalResult;
import lotto.domain.WinningLotto;
import lotto.domain.WinningResult;
import lotto.util.StringUtil;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoApplication {
	public static void main(String[] args) {
		try {
			int inputMoney = InputView.inputMoney();
			Money money = Money.valueOf(inputMoney);
			LottoCount count = money.calculatePurchaseCount();
			OutputView.printLottoCount(count);

			LottoTicketsGenerator lottoTicketsGenerator = new LottoTicketsGenerator(new RandomLottoTicketGenerator());
			LottoTickets lottos = lottoTicketsGenerator.createLottosByCount(count);
			OutputView.printLottos(lottos);

			LottoTicket winningLottoTicket = LottoTicket.of(
				StringUtil.splitRawLottoNumbers(InputView.inputWinningLotto()));
			LottoBall bonusBall = LottoBall.valueOf(InputView.inputWinningBonusBall());
			WinningLotto winningLotto = new WinningLotto(winningLottoTicket, bonusBall);
			WinningResult result = winningLotto.calculateResult(lottos);
			OutputView.printStatistics(new TotalResult(result, money));
		} catch (RuntimeException ex) {
			OutputView.printExceptionMessage(ex.getMessage());
		}
	}
}
