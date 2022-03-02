package lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.Money;
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
		int totalCount = Lotto.countTickets(money);
		int manualCount = InputView.askManualLottoCount(totalCount);
		Lottos lottos = Lottos.purchase(totalCount, manualCount, InputView.askManualLottoNumbers(manualCount));

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

	private WinningBalls getWinningNumbers() {
		List<String> winningNumbersInput = Arrays.asList(InputView.askWinningNumbers());

		return WinningBalls.from(winningNumbersInput);
	}

	private BonusBall getBonusNumber(WinningBalls winningBalls) {
		String bonusNumberInput = InputView.askBonusNumber();

		return BonusBall.from(LottoBall.from(bonusNumberInput), winningBalls);
	}

}
