import controller.LotteryGameController;
import view.InputView;
import view.OutputView;

public class Application {
	public static void main(String[] args) {
		LotteryGameController lotteryGameController = new LotteryGameController(new InputView(), new OutputView());
		lotteryGameController.startLotteryGame();
	}
}
