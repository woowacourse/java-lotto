import java.util.List;

import domain.GameResult;
import domain.Lotto;
import domain.LottoAmount;
import domain.LottoFactory;
import domain.LottoGame;
import domain.Lottos;
import domain.Money;
import domain.WinningLotto;
import view.InputView;
import view.OutputView;

public class Main {
	public static void main(String[] args) {
		Money purchaseMoney = new Money(InputView.inputPurchaseMoney());
		LottoAmount lottoAmount = new LottoAmount(purchaseMoney, InputView.inputSelfNumberLottoAmount());
		Lottos lottos = drawLottos(lottoAmount);

		OutputView.printAmount(lottoAmount);
		OutputView.printLottos(lottos);

		WinningLotto winningLotto = new WinningLotto(InputView.inputSixNumbers(), InputView.inputBonusNumber());
		LottoGame lottoGame = new LottoGame(lottos, winningLotto);
		GameResult gameResult = GameResult.create(lottoGame);

		OutputView.printStatistics(gameResult, purchaseMoney);
	}

	private static Lottos drawLottos(LottoAmount lottoAmount) {
		int autoLottoAmount = lottoAmount.getAutoLottoAmount();
		List<Lotto> selfLottos = LottoFactory.createSelfLottos(InputView.inputSelfNumbers(lottoAmount));
		List<Lotto> autoLottos = LottoFactory.createAutoLottos(autoLottoAmount);
		return new Lottos(selfLottos, autoLottos);
	}
}
