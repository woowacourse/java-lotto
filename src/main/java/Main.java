import java.util.List;

import domain.Lotto;
import domain.LottoFactory;
import domain.LottoNumber;
import domain.Money;
import domain.Rank;
import domain.WinningNumbers;
import view.InputView;
import view.OutputView;

public class Main {
	public static void main(String[] args) {
		Money purchaseMoney = new Money(InputView.inputPurchaseMoney());
		List<Lotto> lottos = LottoFactory.createLottos(purchaseMoney);

		OutputView.printLottos(lottos);

		List<LottoNumber> sixNumbers = InputView.inputSixNumbers();
		LottoNumber bonusNumber = InputView.inputBonusNumber();
		WinningNumbers winningNumbers = new WinningNumbers(sixNumbers, bonusNumber);

		List<Rank> ranks = winningNumbers.compareLottos(lottos);

		OutputView.printResult(ranks);
		OutputView.printProfit(purchaseMoney, ranks);
	}
}
