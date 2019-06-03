package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoCount;
import lotto.domain.LottoResult;
import lotto.domain.Lottos;

import static lotto.domain.Rank.*;

public class OutputConsole {

    public static void outputLotto(Lottos lottos, LottoCount lottoCount) {
        System.out.println("\n수동으로 " + lottoCount.getManualCount() + "장, " +
                "자동으로 " + lottoCount.getAutoCount() + "장을 구매했습니다.");
        StringBuilder stringBuilder = new StringBuilder();
        for (Lotto lotto : lottos.getList()) {
            stringBuilder.append("[");
            stringBuilder.append(String.join(",", lotto.convertStringList()));
            stringBuilder.append("]\n");
        }
        System.out.println(stringBuilder.toString());
    }

    public static void outputResult(LottoResult lottoResult) {
        System.out.println("\n당첨 통계\n----------");
        System.out.println("3개 일치 (5,000원) - " + lottoResult.getCountOfRank(FIFTH) + "개");
        System.out.println("4개 일치 (50,000원) - " + lottoResult.getCountOfRank(FOURTH) + "개");
        System.out.println("5개 일치 (1,500,000원) - " + lottoResult.getCountOfRank(THIRD) + "개");
        System.out.println("5개 일치,보너스 볼 일치 (30,000,000원) - " + lottoResult.getCountOfRank(SECOND) + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + lottoResult.getCountOfRank(FIRST) + "개");
        System.out.println("총 수익률은 " + lottoResult.getEarningsRate() + "% 입니다.");
    }
}
