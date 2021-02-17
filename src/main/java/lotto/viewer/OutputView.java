package lotto.viewer;

import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.Lottos;

public class OutputView {

    private static final String FIFTH_RANK = "3개 일치 (5000원)- %d개";
    private static final String FOURTH_RANK = "4개 일치 (50000원)- %d개";
    private static final String THIRD_RANK = "5개 일치 (1500000원)- %d개";
    private static final String SECOND_RANK = "5개 일치, 보너스 볼 일치(3000000000원)- %d개";
    private static final String FIRST_RANK = "6개 일치 (2000000000원)- %d개";

    public void printPurchasedLottos(Lottos lottos) {
        List <Lotto> lottoBunch = lottos.getLottoBunch() ;
        System.out.printf("%d를 구매하였습니다." + System.lineSeparator(), lottoBunch.size());
        for (Lotto lotto : lottoBunch) {
            System.out.println(lotto.getNumbers());
        }
        System.out.println();
    }

    public void printLottoStatistics(Map<String, Integer> statistics, double profitRate) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.printf(FIFTH_RANK + System.lineSeparator(), statistics.get("FIFTH"));
        System.out.printf(FOURTH_RANK + System.lineSeparator(), statistics.get("FOURTH"));
        System.out.printf(THIRD_RANK + System.lineSeparator(), statistics.get("THIRD"));
        System.out.printf(SECOND_RANK + System.lineSeparator(), statistics.get("SECOND"));
        System.out.printf(FIRST_RANK + System.lineSeparator(), statistics.get("FIRST"));
        System.out.printf("총 수익률은 %.2f입니다.", profitRate);
    }
}