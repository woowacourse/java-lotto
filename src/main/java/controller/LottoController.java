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
        LottoCounter lottoCounter = LottoCounter.of(Lotto.calculateLottoNumber(purchaseMoney),
                InputView.inputPurchasingPassiveLottoNumber());
        Lottos lottos = generatePassiveLottos(lottoCounter.getNumberOfManualLotto());
        lottos.addAll(LottoFactory.generateAutoLottos(new DefaultShuffleStrategy(), lottoCounter.getNumberOfAutoLotto()));

        OutputView.printNumberOfPurchaseLotto(lottoCounter.getNumberOfManualLotto(), lottoCounter.getNumberOfAutoLotto());
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
