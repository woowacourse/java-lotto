package lotto.view;

import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.Rank;

public class ResultView {

    public static void printResult(Lottos lottos) {
        printLottoCount(lottos);
        printBuyingLottos(lottos);
    }

    private static void printLottoCount(Lottos lottos) {
        System.out.println(lottos.getCount() + "개를 구매했습니다.");
    }

    private static void printBuyingLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            printBuyingLotto(lotto);
        }
    }

    private static void printBuyingLotto(Lotto lotto) {
        System.out.println(lotto.getNumbers());
    }

    public static void printTotalResult(Lottos lottos) {
        System.out.println();
        System.out.println("당첨통계");
        System.out.println("---------");
        lottos.getRankCount().forEach((rank, integer) -> {
            System.out.print(printRank(rank, integer));
        });
        printRevenue(lottos);
    }

    private static String printRank(Rank rank, Integer integer) {
        StringBuilder message = new StringBuilder();
        if (!rank.equals(Rank.LOSER)) {
            message.append(rank.getCount()).append("개 일치");
            message.append(printBonusNumber(rank));
            message.append(" (").append(rank.getPrice()).append("원) - ").append(integer).append("개\n");
        }
        return message.toString();
    }

    private static String printBonusNumber(Rank rank) {
        if (rank.isWinBonusNumber()) {
            return ", 보너스 볼 일치";
        }
        return "";
    }

    public static void printRevenue(Lottos lottos) {
        System.out.println("총 수익률은 " + lottos.getRevenue() + "입니다.");
    }
}
