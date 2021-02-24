package lotto.view;

import com.google.common.primitives.Ints;
import lotto.domain.Lotto;
import lotto.domain.Result;
import lotto.domain.Statistics;
import lotto.util.LottoFactory;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class OutputView {
    private static final String SECOND = ", 보너스 볼 일치";

    private OutputView() {
    }

    public static void showBuyLotto(LottoFactory manualLotto, LottoFactory autoLotto) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", manualLotto.getLottos().size(), autoLotto.getLottos().size());
        StringBuilder sb = new StringBuilder();
        showLottoNumbers(sb, manualLotto.getLottos());
        showLottoNumbers(sb, autoLotto.getLottos());
        System.out.println(sb.toString());
    }

    private static void showLottoNumbers(StringBuilder sb, List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            sb.append("[");
            sb.append(Ints.join(", ", lotto.getLottoNumbers().stream().mapToInt(i -> i).toArray()));
            sb.append("]\n");
        }
    }

    public static void result(Statistics statistics) {
        Arrays.stream(Result.values())
                .filter(result -> !result.equals(Result.NONE))
                .sorted(Comparator.comparingInt(Result::getCount))
                .forEach(result ->
                        System.out.printf(
                                "%d개 일치%s(%d원)- %d개\n",
                                result.getCount(),
                                bonusMessage(result),
                                result.getPrize(),
                                statistics.getRankCount(result)
                        )
                );
    }

    private static String bonusMessage(Result result) {
        String message = " ";
        if (result == Result.SECOND) {
            return SECOND;
        }
        return message;
    }

    public static void showTotalProfit(float profit) {
        System.out.printf("총 수익률은 %.2f입니다.\n", profit);
    }

    public static void resultMessage() {
        System.out.println("당첨 통계");
        System.out.println("---------");
    }
}
