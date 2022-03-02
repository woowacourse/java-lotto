package lotto;

import java.util.Arrays;
import java.util.List;

import lotto.model.AutoLotto;
import lotto.model.Lottos;
import lotto.model.Money;
import lotto.model.dto.LottoDTO;
import lotto.model.dto.PrizeInformationDTO;
import lotto.model.number.BonusBall;
import lotto.model.number.LottoBall;
import lotto.model.number.WinningBalls;
import lotto.model.prize.MatchResult;
import lotto.model.prize.PrizeInformations;
import lotto.view.InputView;
import lotto.view.ResultView;

public class Controller {

	public void run() {
		Money money = Money.from(InputView.askMoneyAmount());

		Lottos lottos = purchaseLottos(money);

		WinningBalls winningBalls = getWinningNumbers();
		PrizeInformations prizeInformations =
				getPrize(lottos, winningBalls, getBonusNumber(winningBalls));

		ResultView.showEarningRate(prizeInformations.calculateEarningRate(money));
	}

	private PrizeInformations getPrize(
			Lottos lottos, WinningBalls winningBalls, BonusBall bonusBall) {
		List<MatchResult> matchResults = lottos.match(winningBalls, bonusBall);

		return getPrizeInformations(matchResults);
	}

	private PrizeInformations getPrizeInformations(List<MatchResult> matchResults) {
		PrizeInformations prizeInformations = PrizeInformations.from(matchResults);
		ResultView.showPrizeInformation(PrizeInformationDTO.from(prizeInformations));

		return prizeInformations;
	}

	private Lottos purchaseLottos(Money money) {
		int purchaseCount = getPurchaseCount(money);

		return purchaseLottos(purchaseCount);
	}

	private int getPurchaseCount(Money money) {
		int purchaseCount = AutoLotto.countAvailableTickets(money);
		ResultView.showPurchaseCount(purchaseCount);

		return purchaseCount;
	}

	private Lottos purchaseLottos(int purchaseCount) {
		Lottos lottos = Lottos.purchase(purchaseCount);
		ResultView.showLottos(LottoDTO.from(lottos));

		return lottos;
	}

	private WinningBalls getWinningNumbers() {
		List<String> winningNumbersInput = Arrays.asList(InputView.askWinningNumbers());

		return WinningBalls.from(winningNumbersInput);
	}

	private BonusBall getBonusNumber(WinningBalls winningBalls) {
		String bonusNumberInput = InputView.askBonusNumber();

		return BonusBall.from(LottoBall.from(bonusNumberInput), winningBalls);
	}

}
