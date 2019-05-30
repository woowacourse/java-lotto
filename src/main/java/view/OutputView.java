package view;

import model.*;

public class OutputView {
    public static void printPurchaseAmount(int manual, int auto) {
        System.out.println("\n수동으로 " + manual + "장, 자동으로 " + auto + "개를 구매했습니다.");
    }

    public static void printLottos(Lottos lottos) {
        lottos.forEach(lotto -> System.out.println(lotto));
    }

    public static void printResult(LottoResult result) {
        printWinningNumbers(result);
        System.out.println("\n당첨 통계\n---------");
        result.forEach(x -> {
            System.out.println(
                    x.getKey().getNumberOfMatches()
                    + ((x.getKey().equals(LottoRank.SECOND)) ? "개 일치, 보너스 볼 일치 (" : "개 일치 (")
                    + x.getKey().getPrize()
                    + "원)- "
                    + x.getValue()
                    + "개"
            );
        });
        System.out.format("총 수익률은 %d%%입니다.", Math.round(result.getEarningRate()));
    }

    private static void printWinningNumbers(LottoResult result) {
        System.out.println("\n금주의 당첨 번호 : " + result.getWinningNumbers() + " + 보너스 번호 " + result.getBonusNumber());
    }
}
