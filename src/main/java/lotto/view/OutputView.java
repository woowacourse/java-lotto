package lotto.view;

import java.util.List;
import lotto.domain.Lottos;

public class OutputView {

    public void printCount(int count) {
        System.out.println(String.format("%d개를 구매했습니다.", count));
    }

    public void printLottos(Lottos lottos) {
        System.out.println(lottos.toString());
    }

    public void printResult(String result) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
        System.out.println(result);
    }

    public void printProfitRate(double profitRate) {
        System.out.println(String.format("총 수익률은 %.2f입니다.", profitRate));
    }
}
