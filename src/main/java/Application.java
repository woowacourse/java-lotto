import controller.LotteryGameController;

public class Application {
	public static void main(String[] args) {
		LotteryGameController lotteryGameController = new LotteryGameController();
		lotteryGameController.startLotteryGame();
		lotteryGameController.makeResult();
	}
}
