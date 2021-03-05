package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoController {

    public static void main(String[] args) {
        Money purchaseMoney = Money.createPurchasingLottoMoney(InputView.inputPurchaseMoney());
        int numberOfTotalLotto = Lotto.calculateLottoNumber(purchaseMoney);
        int numberOfPassiveLotto = InputView.inputPurchasingPassiveLottoNumber();
        int numberOfAutoLotto = numberOfTotalLotto - numberOfPassiveLotto;
        Lottos lottos = generatePassiveLottos(numberOfPassiveLotto);
        lottos.addAll(LottoFactory.generateAutoLottos(new DefaultShuffleStrategy(), numberOfAutoLotto));

        OutputView.printNumberOfPurchaseLotto(numberOfPassiveLotto, numberOfAutoLotto);
        OutputView.printAllLottos(lottos);

        WinningLotto winningLotto = new WinningLotto(
                writeValueAsLotto(InputView.inputWinningLotto()),
                LottoNumber.of(InputView.inputBonusBall())
        );
        LottoResults results = lottos.getLottoResults(winningLotto);
        OutputView.printResults(results);

        Money earningMoney = results.getTotalWinningMoney();
        OutputView.printEarningRate(earningMoney.calculateEarningRate(purchaseMoney));
    }

    private static Lottos generatePassiveLottos(final int numberOfPassiveLotto) {
        OutputView.printInputPassiveLottos();
        List<Lotto> lottos = Stream.generate(InputView::inputPurchasingPassiveLotto)
                .map(LottoController::writeValueAsLotto)
                .limit(numberOfPassiveLotto)
                .collect(Collectors.toList());
        return new Lottos(lottos);
    }

    private static Lotto writeValueAsLotto(List<Integer> lottoNumber) {
        return new Lotto(lottoNumber.stream()
                .map(LottoNumber::of)
                .collect(Collectors.toList()));
    }
}
