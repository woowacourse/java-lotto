import domain.Lotto;
import domain.LottoFactory;
import domain.Rank;
import domain.WinningNumbers;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        int purchaseMoney = InputView.inputPurchaseMoney();
        List<Lotto> lottos =  LottoFactory.createLottos(purchaseMoney);

        OutputView.printLottos(lottos);

        List<Integer> sixNumbers = InputView.inputSixNumbers();
        int bonusNumber = InputView.inputBonusNumber();
        WinningNumbers winningNumbers = new WinningNumbers(sixNumbers, bonusNumber);

        List<Rank> ranks = winningNumbers.compareLottos(lottos);

        OutputView.printResult(ranks);
        OutputView.printProfit(purchaseMoney, ranks);
    }
}
