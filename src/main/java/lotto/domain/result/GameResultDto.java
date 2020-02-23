package lotto.domain.result;

public class GameResultDto {
	private final GameResult gameResult;
	private final double profit;

	public GameResultDto(GameResult result, double profit) {
		this.gameResult = result;
		this.profit = profit;
	}

	public GameResult getGameResult() {
		return gameResult;
	}

	public double getProfit() {
		return profit;
	}
}
