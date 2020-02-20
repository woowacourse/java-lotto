package lotto.view;

import lotto.domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String PURCHASE_NUMBER_POSTFIX = "개를 구입했습니다.";

    public static void printPurchaseNumber(PurchaseAmount purchaseAmount) {
        System.out.println(purchaseAmount.getPurchaseNumber() + PURCHASE_NUMBER_POSTFIX);
        System.out.println();
    }

    public static void printPurchaseLottoNumbers(PurchaseLottos purchaseLottos) {
        for (Lotto lotto : purchaseLottos.getPurchaseLottos()) {
            printLottoNumbers(lotto);
        }
        System.out.println();
    }

    private static void printLottoNumbers(Lotto lotto) {
        List<LottoNumber> lottoNumberGroups = lotto.getLottoNumbers();
        String output = lottoNumberGroups.stream()
                .map(LottoNumber::getLottoNumber)
                .map(Object::toString)
                .collect(Collectors.joining(", ", "[", "]"));
        System.out.println(output);
    }

    public static void printResult(PurchaseAmount purchaseAmount) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        for (Rank rank : Rank.values()) {
            printRankResult(rank);
        }
        System.out.println("총 수익률은 " + ResultCalculator.calculateEarningRate(purchaseAmount) + "%입니다.");
    }

    private static void printRankResult(Rank rank) {
        System.out.printf("%d개 일치", rank.correctLottoNumber);
        if (rank.isCorrectBonusNumber == true) {
            System.out.printf(", 보너스 볼 일치");
        }
        System.out.printf(" (%d원) - %d개 \n", rank.prize, rank.count);
    }
}
