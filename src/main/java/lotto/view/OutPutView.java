package lotto.view;

import lotto.domain.LottoResult;
import lotto.domain.LottoTicket;
import lotto.domain.Rank;

public class OutPutView {
    public static final String ENTER = "\n";

    public static void showLottoTicket(LottoTicket lottoTicket) {
        System.out.println(ENTER + "수동으로 "
                + lottoTicket.getNumberOfCustomLotto()
                + "장, 자동으로 "
                + lottoTicket.getNumberOfAutoLotto()
                + "장을 구매 했습니다.");
        System.out.println(lottoTicket + ENTER);
    }

    public static void showLottoResult(LottoResult lottoResult) {
        System.out.println(ENTER + "당첨 통계");
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

        System.out.println(ENTER + "총 수익률은 "
                + String.format("%.1f", lottoResult.dividendRate())
                + "% 입니다.");
    }

}
