package lotto.view;

import com.google.common.primitives.Ints;
import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Result;
import lotto.domain.Statistics;

import java.util.Arrays;
import java.util.Comparator;

public class OutputView {

    private OutputView() {
    }

    public static void showBuyLotto(int manualCount, int autoCount) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", manualCount, autoCount);
        StringBuilder sb = new StringBuilder();
        for (Lotto lotto : Lottos.getPurchaseLottos()) {
            sb.append("[");
            sb.append(Ints.join(", ", lotto.getLottoNumbers().stream().mapToInt(i -> i).toArray()));
            sb.append("]\n");
        }
        System.out.println(sb.toString());
    }

    public static void resultMessage(Statistics statistics, float profit) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        result(statistics);
        showTotalProfit(profit);
    }

    private static void result(Statistics statistics) {
        Arrays.stream(Result.values())
                .filter(result -> !result.equals(Result.NONE))
                .sorted(Comparator.comparingInt(Result::getCount))
                .forEach(result ->
                        System.out.printf(
                                "%d개 일치%s(%d원)- %d개\n",
                                result.getCount(),
                                result.getBonus(),
                                result.getPrize(),
                                statistics.getRankCount(result)
                        )
                );
    }

    private static void showTotalProfit(float profit) {
        System.out.printf("총 수익률은 %.2f입니다.\n", profit);
    }
}
