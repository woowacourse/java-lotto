package lotto.view;

import lotto.domain.LottoBuyer;
import lotto.domain.WinningResult;

import java.util.List;

public class OutputView {
    private static final String NEW_LINE = System.getProperty("line.separator");

    public static void printErrorMsg(Exception e) {
        System.out.println(e.getMessage() + NEW_LINE);
    }

    public static void printContainingLottos(LottoBuyer person) {
        List<String> lottos = person.showLottos();
        System.out.println(NEW_LINE + lottos.size() + "개를 구매하셨습니다.");
        for (String lotto : lottos) {
            System.out.println(lotto);
        }
        System.out.print(NEW_LINE);
    }

    public static void printResult(WinningResult winningResult) {
        int[] rankCount = winningResult.getRankCounter();
        System.out.println(NEW_LINE + "당첨 통계" + NEW_LINE + "---------");
        System.out.println("3개 일치 (5000원) - " + rankCount[1] + "개");
        System.out.println("4개 일치 (50000원) - " + rankCount[2] + "개");
        System.out.println("5개 일치 (1500000원) - " + rankCount[3] + "개");
        System.out.println("5개 일치, 보너스 볼 일치(30000000원) - " + rankCount[4] + "개");
        System.out.println("6개 일치 (2000000000원) - " + rankCount[5] + "개");
        System.out.println("총 수익률은 "
                + ((long) winningResult.getWinningMoney() * 100 / winningResult.getSpendMoney())
                + "% 입니다.");
    }
}