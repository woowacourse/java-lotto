package lotto;

import view.InputView;
import view.OutputView;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        int purchaseMoney = InputView.inputPurchaseMoney();
        List<Lotto> lottos =  LottoFactory.createLottos(purchaseMoney);
        OutputView.printLottos(lottos);

        WinningNumbers winningNumbers = new WinningNumbers(InputView.inputSixNumbers(), InputView.inputBonusNumber());
        List<Rank> ranks = winningNumbers.compareLottos(lottos);
        OutputView.printResult(ranks);
        OutputView.printProfit(purchaseMoney, ranks);
    }
}
