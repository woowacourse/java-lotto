package lotto;

import java.util.List;

import lotto.model.lotto.Lotto;
import lotto.model.lotto.Lottos;
import lotto.model.Money;
import lotto.model.dto.LottoDTO;
import lotto.model.dto.PrizeInformationDTO;
import lotto.model.lotto.BonusBall;
import lotto.model.lotto.LottoBall;
import lotto.model.lotto.WinningBalls;
import lotto.model.prize.MatchResult;
import lotto.model.prize.PrizeInformations;
import lotto.view.InputView;
import lotto.view.ResultView;

public class Controller {

	public void run() {
		Money money = getMoney();
		int totalCount = getTotalCount(money);
		int manualCount = getManualCount(totalCount);
		Lottos lottos = purchase(totalCount, manualCount);

		showPurchase(totalCount, manualCount, lottos);

		WinningBalls winningBalls = getWinningNumbers();
		PrizeInformations prizeInformations =
				getPrize(lottos, winningBalls, getBonusNumber(winningBalls));

		ResultView.showEarningRate(prizeInformations.calculateEarningRate(money));
	}

	private Money getMoney() {
		try {
			return Money.from(InputView.askMoneyAmount());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return getMoney();
		}
	}

	private int getTotalCount(Money money) {
		try {
			return Lotto.countTickets(money);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return getTotalCount(money);
		}
	}

	private int getManualCount(int totalCount) {
		try {
			return InputView.askManualLottoCount(totalCount);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return getManualCount(totalCount);
		}
	}

	private Lottos purchase(int totalCount, int manualCount) {
		try {
			return Lottos.purchase(totalCount, manualCount, InputView.askManualLottoNumbers(manualCount));
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return purchase(totalCount, manualCount);
		}
	}

	private void showPurchase(int totalCount, int manualCount, Lottos lottos) {
		ResultView.showPurchaseCount(totalCount, manualCount);
		ResultView.showLottos(LottoDTO.from(lottos));
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

	private WinningBalls getWinningNumbers() {
		try {
			return WinningBalls.from(InputView.askWinningNumbers());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return getWinningNumbers();
		}
	}

	private BonusBall getBonusNumber(WinningBalls winningBalls) {
		try {
			return BonusBall.from(LottoBall.from(InputView.askBonusNumber()), winningBalls);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			return getBonusNumber(winningBalls);
		}

	}

}
