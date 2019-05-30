package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;
import lotto.domain.WinningLotto;

import static lotto.domain.Rank.*;

public class OutputConsole {

    public static void outputLotto(Lottos lottos, int numberOfManualLotto, int numberOfAutoLotto) {
        System.out.println("수동으로 " + numberOfManualLotto + "장, " +
                "자동으로 " + numberOfAutoLotto + "장을 구매했습니다.");
        StringBuilder stringBuilder = new StringBuilder();
        for (Lotto lotto : lottos.getList()) {
            stringBuilder.append("[");
            stringBuilder.append(String.join(",", lotto.convertStringList()));
            stringBuilder.append("]\n");
        }
        System.out.println(stringBuilder.toString());
    }

    public static void outputResult(WinningLotto winningLotto, Lottos lottos) {
        System.out.println("\n당첨 통계\n----------");
        LottoResult lottoResult = new LottoResult(winningLotto, lottos);
        System.out.println("3개 일치 (5,000원) - " + lottoResult.getCountOfRank(FIFTH) + "개");
        System.out.println("4개 일치 (50,000원) - " + lottoResult.getCountOfRank(FOURTH) + "개");
        System.out.println("5개 일치 (1,500,000원) - " + lottoResult.getCountOfRank(THIRD) + "개");
        System.out.println("5개 일치,보너스 볼 일치 (30,000,000원) - " + lottoResult.getCountOfRank(SECOND) + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + lottoResult.getCountOfRank(FIRST) + "개");
        System.out.println("총 수익률은 " + lottoResult.getEarningsRate() + "% 입니다.");
    }
}
