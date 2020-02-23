import java.util.List;

import domain.GameResult;
import domain.Lotto;
import domain.LottoFactory;
import domain.Money;
import domain.ProfitCalculator;
import domain.PurchaseMoney;
import domain.WinningNumbers;
import view.InputView;
import view.OutputView;

public class Main {
	public static void main(String[] args) {
		try {
			Money purchaseMoney = new PurchaseMoney(InputView.inputPurchaseMoney());
			List<Lotto> lottos = LottoFactory.createLottos(purchaseMoney);

			OutputView.printLottos(lottos);

			WinningNumbers winningNumbers = new WinningNumbers(InputView.inputSixNumbers(),
				InputView.inputBonusNumber());
			GameResult gameResult = new GameResult(winningNumbers.compareLottos(lottos));

			OutputView.printResult(gameResult);
			OutputView.printProfit(ProfitCalculator.getProfit(purchaseMoney, gameResult));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
