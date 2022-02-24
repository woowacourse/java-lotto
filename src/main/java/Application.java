import controller.LotteryGameController;
import view.InputView;

public class Application {
	public static void main(String[] args) {
		LotteryGameController lotteryGameController = new LotteryGameController(new InputView());
		lotteryGameController.startLotteryGame();
		lotteryGameController.makeResult();
	}
}
