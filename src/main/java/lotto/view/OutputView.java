package lotto.view;

import java.util.List;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Money;
import lotto.domain.WinningType;

public class OutputView {

    public static void printLottos(int manualSize, List<Lotto> lottos) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.%n", manualSize, lottos.size() - manualSize);

        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
    }

    public static void printLottoResult(LottoResult lottoResult) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");

        for (WinningType value : WinningType.values()) {
            if (value == WinningType.MISS) {
                continue;
            }
            if (value == WinningType.SECOND) {
                System.out.printf("%d개 일치, 보너스 볼 일치(%d원)- %d개.%n", value.getMatchNum(), value.getReward(), lottoResult.getWinnerTypeValue(value));
                continue;
            }
            System.out.printf("%d개 일치 (%d원)- %d개.%n", value.getMatchNum(), value.getReward(), lottoResult.getWinnerTypeValue(value));
        }

    }

    public static void printLottoYield(LottoResult lottoResult, Money money) {
        double result = ((double) lottoResult.getRewardAll() / money.getMoney()) * 100;
        System.out.println("총 수익률은 " + result + "%입니다.");
    }
}
