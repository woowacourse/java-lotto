package lotto.view;

import lotto.domain.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String SECOND = ", 보너스 볼 일치";

    private OutputView() {
    }

    public static void showBuyLotto(Lottos lottos, PurchaseCount purchaseCount) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", purchaseCount.getManualPurchaseCount(), purchaseCount.getAutoPurchaseCount());
        StringBuilder sb = new StringBuilder();
        showLottoNumbers(sb, lottos.getLottos());
        System.out.println(sb.toString());
    }

    private static void showLottoNumbers(StringBuilder sb, List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            sb.append("[");
            sb.append(lotto.getLottoNumbers().stream()
                    .map(lottoNumber -> lottoNumber.getLottoNumber())
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "))
            );
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
