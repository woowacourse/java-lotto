package lotto.domain.view;

import lotto.domain.model.Lotto;
import lotto.domain.model.Money;
import lotto.domain.model.PurchasedLottos;
import lotto.domain.model.Rank;

import java.util.Map;

public class OutputView {

    private static final String NEW_LINE ="\n";

    public static void printPurchasedLottoResult(PurchasedLottos purchasedLottos, int manualLottoSize) {
        System.out.println(NEW_LINE + "수동으로" + manualLottoSize + "장, 자동으로 " + (purchasedLottos.size() - manualLottoSize) + "개를 구매했습니다.");
        for (Lotto lotto: purchasedLottos.getLottos()) {
            System.out.println(lotto.toString());
        }
    }

    public static void printLottoResult(Money money, Map<Rank, Integer> prizeResult) {
        int prizeSum = 0;
        for (Rank rank : Rank.values()) {
            if (rank != Rank.MISS) {
                prizeSum += prizeResult.get(rank);
                System.out.println(rank.toString() + (prizeResult.get(rank) / rank.getPrize()) + "개");
            }
        }
        System.out.println(prizeSum);
        printProfitRate(money, prizeSum);
    }

    private static void printProfitRate(Money money, double prizeSum) {
        double profitRate = prizeSum / (double) money.getMoney() ;
        System.out.println("총 수익률은 " + Math.round(profitRate * 1000) / 1000.0 + "입니다.");
    }
}
