package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Money;
import lotto.domain.WinPrize;

import java.util.List;

public interface OutputView {

    void printResult(final WinPrize winPrize);

    void printRateOfProfit(final Money money, final WinPrize winPrize);

    void printLottos(List<Lotto> userLottos, int countOfManual, long countOfPurchase);
}
