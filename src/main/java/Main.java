import java.util.List;

import domain.GameResult;
import domain.Lotto;
import domain.LottoGame;
import domain.Money;
import view.InputView;
import view.OutputView;

public class Main {
	public static void main(String[] args) {
		try {
			Money purchaseMoney = new Money(InputView.inputPurchaseMoney());
			int cnt = InputView.inputSelfNumberLottoAmount();
			for (int i = 0 ; i< cnt;i++){
				InputView.inputSixNumbers();
			}

			LottoGame lottoGame = new LottoGame(purchaseMoney);
			OutputView.printLottos(lottoGame);

			lottoGame.play(InputView.inputSixNumbers(),
				InputView.inputBonusNumber());
			GameResult gameResult = GameResult.create(lottoGame);

			OutputView.printStatistics(gameResult, purchaseMoney);
		} catch (Exception e) {
			OutputView.printErrorMessage(e.getMessage());
		}
	}
}
