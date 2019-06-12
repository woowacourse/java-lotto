package lotto.view;

import lotto.dto.LottoDto;
import lotto.domain.*;

import java.util.List;

public class OutputView {
    private static final String DELIMITER = ", ";
    private static final String LEFT_BRACKET = "[";
    private static final String RIGHT_BRACKET = "]";

    private OutputView() {

    }

    public static void showBuyCounts(final int manualPurchaseCount, final int autoPurchaseCount) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", manualPurchaseCount, autoPurchaseCount);
    }

    public static void showGameResult(final GameResult gameResult) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        for (Rank rank : Rank.reverseValues()) {
            showRank(rank);
            System.out.println(gameResult.getRankCount(rank) + "개");
        }
        System.out.printf("총 수익률은 %.1f 퍼센트입니다.", gameResult.profit(LottoMachine.LOTTO_MONEY));
    }

    private static void showRank(final Rank rank) {
        System.out.print(rank.getMatchCount() + "개 일치");
        if (rank == Rank.SECOND) {
            System.out.print(", 보너스 볼 일치");
        }
        System.out.print("(" + rank.getMoney() + ")- ");
    }

    public static void showLottos(final List<LottoDto> lottos) {
        for (final LottoDto lotto : lottos) {
            showLotto(lotto);
            System.out.println();
        }
    }

    private static void showLotto(LottoDto lotto) {
        System.out.print(LEFT_BRACKET);
        System.out.print(String.join(DELIMITER, lotto.getNumbers()));
        System.out.print(RIGHT_BRACKET);
    }
}
