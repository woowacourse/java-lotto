import domain.GameResult;
import domain.LottoGame;
import domain.Money;
import domain.ProfitCalculator;
import domain.PurchaseMoney;
import view.InputView;
import view.OutputView;

public class Main {
	public static void main(String[] args) {
		try {
			Money purchaseMoney = new PurchaseMoney(InputView.inputPurchaseMoney());
			LottoGame lottoGame = new LottoGame(purchaseMoney);
			OutputView.printLottos(lottoGame);

			GameResult gameResult = lottoGame.play(InputView.inputSixNumbers(),
				InputView.inputBonusNumber());

			OutputView.printResult(gameResult);
			OutputView.printProfit(ProfitCalculator.getProfit(purchaseMoney, gameResult));
		} catch (Exception e) {
			OutputView.printErrorMessage(e.getMessage());
		}
	}
}
