package lotto;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        int money = InputView.inputPurchaseMoney();
        List<Lotto> lottos =  LottoFactory.createLottos();
        OutputView.printLottos(lottos);

        WinnigNumbers winnigNumbers = new WinnigNumbers(InputView.inputSixNumbers(), InputView.inputBonusNumber());
        List<Rank> ranks = winnigNumbers.compare(lottos);
        OutputView.printResult(ranks);
        OutputView.printProfit(money, ranks);
    }
}
