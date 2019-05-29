package lotto.view;

import java.util.List;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Money;
import lotto.domain.WinningType;

public class OutputView {

    public static void printLottos(List<Lotto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");

        for (Lotto lotto : lottos) {
            System.out.println(lotto);
        }
    }

    public static void printLottoResult(LottoResult lottoResult) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");

        for (WinningType value : WinningType.values()) {
            System.out.print(value.getMatchNum() + "개 일치 (");
            System.out.print(value.getReward() + "원)- ");
            System.out.println(lottoResult.getWinnerTypeValue(value) + "개");
        }

    }

    public static void printLottoYield(LottoResult lottoResult, Money money) {
        double result = ((double) lottoResult.getRewardAll() / money.getMoney()) * 100;
        System.out.println("총 수익률은 " + result + "%입니다.");
    }
}
