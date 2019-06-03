package view;

import model.*;

public class OutputView {
    public static void printPurchaseAmount(LottoPurchaseAmount purchaseAmount) {
        System.out.println(
                "\n수동으로 " + purchaseAmount.manual() + "장, 자동으로 " + purchaseAmount.auto() + "개를 구매했습니다."
        );
    }

    public static void printLottos(Lottos lottos) {
        lottos.forEach(lotto -> System.out.println(lotto));
    }

    public static void printWinningNumbers(WinningNumbers winningNumbers) {
        System.out.println(
            "\n금주의 당첨 번호 : "
            + winningNumbers.mainNumbers()
            + " + 보너스 번호 "
            + winningNumbers.bonusNumber()
        );
    }

    public static void printResult(LottoResult result) {
        System.out.println("\n당첨 통계\n---------");
        result.forEach((rank, number) -> {
            System.out.println(
                    rank.numberOfMatches()
                    + ((rank.equals(LottoRank.SECOND)) ? "개 일치, 보너스 볼 일치 (" : "개 일치 (")
                    + rank.prize()
                    + "원)- "
                    + number
                    + "개"
            );
        });
        System.out.format("총 수익률은 %d%%입니다.", Math.round(result.earningRate()));
    }
}
