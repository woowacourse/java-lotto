package lotto.view;

import lotto.domain.*;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String PURCHASE_NUMBER_POSTFIX = "개를 구입했습니다.";
    public static final String PRINT_EARNING_RATE_FORMAT = "총 수익률은 %d%%입니다.\n";
    public static final String PRINT_CORRECT_LOTTO_NUMBER_FORMAT = "%d개 일치";
    public static final String PRINT_BONUS_CORRECT_MESSAGE = ", 보너스 볼 일치";
    public static final String PRINT_RANK_INFO_FORMAT = " (%d원) - %d개 \n";
    public static final String PRINT_WINNING_STATISTICS_MESSAGE = "당첨 통계";
    public static final String PRINT_DIVISION_LINE = "---------";

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

    public static void printResult(PurchaseAmount purchaseAmount, Result result) {
        System.out.println(PRINT_WINNING_STATISTICS_MESSAGE);
        System.out.println(PRINT_DIVISION_LINE);
        for (Rank rank : Rank.values()) {
            printRankResult(rank, result);
        }
        System.out.printf(PRINT_EARNING_RATE_FORMAT, result.calculateEarningRate(purchaseAmount));
    }

    private static void printRankResult(Rank rank, Result result) {
        System.out.printf(PRINT_CORRECT_LOTTO_NUMBER_FORMAT, rank.correctLottoNumber);
        if (rank.isCorrectBonusNumber) {
            System.out.print(PRINT_BONUS_CORRECT_MESSAGE);
        }
        System.out.printf(PRINT_RANK_INFO_FORMAT, rank.prize, result.getRankCount(rank));
    }
}
