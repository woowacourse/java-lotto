package controller;

import domain.Lotto.*;
import domain.Rank;
import domain.ResultStatus;
import domain.LottoCount;
import domain.Money;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoController {

    public Money chargeMoney(String purchaseAmount) {
        return Money.of(purchaseAmount);
    }

    public LottoCount selectLottoCount(Money money, String manualLottoCount) {
        int purchasableLottoCount = money.calculatePurchasableLottoCount();
        return LottoCount.of(manualLottoCount, purchasableLottoCount);
    }

    public Lottos purchaseLotto(LottoCount lottoCount,  List<String> manualNumber) {
        List<Lotto> manualLottos = LottoFactory.generateManualLottos(manualNumber);
        List<Lotto> autoLottos = LottoFactory.generateAutoLottos(lottoCount.getAutoLottoCount());

        List<Lotto> purchasableLottos = Stream.of(manualLottos, autoLottos)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        return new Lottos(purchasableLottos);
    }

    public WinningLotto determineWinningNumber(String winningNumbers, String bonusBall) {
        Lotto winningLotto = LottoFactory.generateManualLotto(winningNumbers);
        return new WinningLotto(winningLotto, LottoNumber.valueOf(bonusBall));
    }

    public ResultStatus makeResult(Lottos lottos, WinningLotto winningLotto) {
        List<Rank> ranks = lottos.judgeAll(winningLotto);
        ResultStatus resultStatus = new ResultStatus();
        resultStatus.judgeResult(ranks);
        return resultStatus;
    }

    public double calculateImcomeRate(ResultStatus resultStatus, Lottos lottos) {
        double totalIncome = resultStatus.calculateTotalIncome();
        return lottos.calculateIncomeRate(totalIncome);
    }
}

