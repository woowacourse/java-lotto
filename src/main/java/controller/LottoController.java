package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class LottoController {

    public static void main(String[] args) {
        Money purchaseMoney = Money.createPurchasingLottoMoney(InputView.inputPurchaseMoney());
        int numberOfTotalLotto = Lotto.calculateLottoNumber(purchaseMoney);
        int numberOfPassiveLotto = InputView.inputPurchasingPassiveLottoNumber();
        int numberOfAutoLotto = numberOfTotalLotto - numberOfPassiveLotto;
        Lottos lottos = LottoFactory.generatesPassiveLottos(
                writeValuesAsLotto(InputView.inputPurchasingPassiveLottos(numberOfPassiveLotto))
        );
        lottos.addAll(LottoFactory.generateAutoLottos(new DefaultShuffleStrategy(), numberOfAutoLotto));

        OutputView.printNumberOfPurchaseLotto(numberOfPassiveLotto, numberOfAutoLotto);
        OutputView.printAllLottos(lottos);

        WinningLotto winningLotto = new WinningLotto(
                new Lotto(InputView.inputWinningLotto()), LottoNumber.of(InputView.inputBonusBall())
        );
        LottoResults results = lottos.getLottoResults(winningLotto);
        OutputView.printResults(results);

        Money earningMoney = results.getTotalWinningMoney();
        OutputView.printEarningRate(earningMoney.calculateEarningRate(purchaseMoney));
    }

    private static List<Lotto> writeValuesAsLotto(List<List<LottoNumber>> values) {
        return values.stream()
                .map(Lotto::new)
                .collect(Collectors.toList());
    }
}
