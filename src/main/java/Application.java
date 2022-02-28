import java.util.List;

import controller.LotteryGameController;
import controller.dto.LotteriesDto;
import controller.dto.WinningResultDto;
import view.InputView;
import view.OutputView;

public class Application {
	public static void main(String[] args) {
		LotteryGameController lotteryGameController = new LotteryGameController();
		purchaseLotteries(lotteryGameController);
		defineWinningLottery(lotteryGameController);
		calculateWinningResult(lotteryGameController);
	}

	private static void purchaseLotteries(final LotteryGameController controller) {
		final int purchaseAmount = InputView.inputValidMoney();
		final LotteriesDto lotteries = controller.purchaseLotteries(purchaseAmount);
		OutputView.printLotteries(lotteries);
	}

	private static void defineWinningLottery(final LotteryGameController controller) {
		final List<Integer> winningNumbers = InputView.inputValidLotteryNumber();
		final int bonusNumber = InputView.inputValidBonusNumber();
		controller.createWinningLottery(winningNumbers, bonusNumber);
	}

	private static void calculateWinningResult(final LotteryGameController controller) {
		WinningResultDto winningResult = controller.getWinningResult();
		OutputView.printStatistics(winningResult);
	}

}
