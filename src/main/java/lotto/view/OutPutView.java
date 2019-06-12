package lotto.view;

import lotto.domain.LottoResult;
import lotto.domain.LottoTicket;
import lotto.domain.Rank;

public class OutPutView {
    public static final String NEW_LINE = "\n";

    public static void showLottoTicket(LottoTicket lottoTicket) {
        System.out.println(NEW_LINE + "수동으로 "
                + lottoTicket.getNumberOfCustomLotto()
                + "장, 자동으로 "
                + lottoTicket.getNumberOfAutoLotto()
                + "장을 구매 했습니다.");
        System.out.println(lottoTicket + NEW_LINE);
    }

    public static void showLottoResult(LottoResult lottoResult) {
        System.out.println(NEW_LINE + "당첨 통계");
        System.out.println("--------------------");

        for (Rank result : lottoResult.getResult().keySet()) {
            if (result.equals(Rank.MISS)) {
                continue;
            }
            System.out.println(result.getCountOfMatch()
                    + "개 일치 ("
                    + result.getWinningMoney()
                    + ") - "
                    + (lottoResult.getResultKey(result))
                    + "개");
        }

        System.out.println(NEW_LINE + "총 수익률은 "
                + String.format("%.1f", lottoResult.dividendRate())
                + "% 입니다.");
    }

}
