package lotto.view;

import com.google.common.primitives.Ints;
import lotto.domain.Lotto;
import lotto.domain.Result;
import lotto.domain.Statistics;

import java.util.List;

public class OutputView {

    private OutputView() {
    }

    public static void showBuyLotto(List<Lotto> lottos) {
        System.out.printf("%d개를 구매했습니다.\n", lottos.size());
        StringBuilder sb = new StringBuilder();
        for (Lotto lotto : lottos) {
            sb.append("[");
            sb.append(Ints.join(", ", lotto.getLottoNumbers().stream().mapToInt(i -> i).toArray()));
            sb.append("]\n");
        }
        System.out.println(sb.toString());
    }

    public static void result(List<Result> values,Statistics statistics) {
        for (Result result : values) {
            System.out.printf("%d개 일치%s(%d원)- %d개\n",
                    result.getCount(),
                    result.getBonus(),
                    result.getPrize(),
                    statistics.getStatic(result)
            );
        }
    }

    public static void showTotalProfit(float profit) {
        System.out.printf("총 수익률은 %.2f입니다.\n", profit);
    }

    public static void resultMessage() {
        System.out.println("당첨 통계");
        System.out.println("---------");
    }
}
