package lotto.view;

import lotto.domain.lotto.LottoResult;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.lotto.Rank;

public class OutPutView {
    private static final String ENTER = "\n";

    public static void showLottoTicket(LottoTicket lottoTicket) {
        System.out.println(lottoTicket.getNumberOfLotto() + "개를 구매 했습니다.");
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
