package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.Lottos;
import lotto.domain.Result;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OutputView {
    public static void showLottos(Lottos lottos) {
        System.out.printf("%d개를 구매했습니다.\n", lottos.getNumberOfLotto());
        for(Lotto lotto : lottos.getLottos()){
            System.out.println(lotto.getNumbers());
        }
    }

//당첨 통계
//---------
//    3개 일치 (5000원)- 1개
//4개 일치 (50000원)- 0개
//5개 일치 (1500000원)- 0개
//5개 일치, 보너스 볼 일치(30000000원) - 0개
//6개 일치 (2000000000원)- 0개
//총 수익률은 0.35입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)

    public static void result(List<Result> results, float profit) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        List<Integer> statistics = Result.getStatistics(results);
        List<Result> values = Arrays.asList(Result.values());
        Collections.reverse(values);
        for (int i = 0; i < Result.values().length; i++) {
            System.out.printf("%d개 일치 (%d원)- %d개\n", values.get(i).getCount(), values.get(i).getPrize(), statistics.get(i));
        }
        System.out.printf("총 수익률은 %f입니다.\n", profit);
    }
}
