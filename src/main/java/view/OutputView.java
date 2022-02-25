package view;

import domain.Lotto;
import domain.LottoRank;
import domain.LottoResult;
import domain.LottoTicket;

import java.util.Map;

public class OutputView {

    private static final String PURCHASED_LOTTO_COUNT_SUFFIX = "개를 구매했습니다.";
    private static final String LOTTO_RESULT_PREFIX = "당첨 통계\n---------";
    private static final String LOTTO_RESULT_WITH_BONUS_BALL_FORMAT = "%d개 일치, 보너스볼 일치(%d원)- %d개\n";
    private static final String LOTTO_RESULT_FORMAT = "%d개 일치 (%d원)- %d개\n";
    private static final String PROFIT_RATE_RESULT_PREFIX = "총 수익률은 ";
    private static final String PROFIT_RATE_RESULT_SUFFIX = "입니다.";

    public static void printPurchasedLotto(LottoTicket lottoTicket) {
        System.out.println(lottoTicket.getLottos().size() + PURCHASED_LOTTO_COUNT_SUFFIX);
        for (Lotto lotto : lottoTicket.getLottos()) {
            System.out.println(lotto);
        }
    }

    public static void printLottoResult(LottoResult lottoResult) {
        System.out.println(LOTTO_RESULT_PREFIX);
        Map<LottoRank, Integer> resultCount = lottoResult.getResultCount();
        for (LottoRank rank : LottoRank.values()) {
            printLottoResultByRank(resultCount, rank);
        }
    }

    public static void printProfitRate(double calculateProfit) {
        System.out.println(PROFIT_RATE_RESULT_PREFIX + calculateProfit + PROFIT_RATE_RESULT_SUFFIX);
    }

    private static void printLottoResultByRank(Map<LottoRank, Integer> resultCount, LottoRank rank) {
        if (rank.equals(LottoRank.RANK_NOTHING)) {
            return;
        }
        if (rank.equals(LottoRank.RANK_2)) {
            System.out.printf(LOTTO_RESULT_WITH_BONUS_BALL_FORMAT,
                    rank.getCount(), rank.getPrice(), resultCount.getOrDefault(rank, 0));
            return;
        }
        System.out.printf(
                LOTTO_RESULT_FORMAT, rank.getCount(), rank.getPrice(), resultCount.getOrDefault(rank, 0));
    }
}
