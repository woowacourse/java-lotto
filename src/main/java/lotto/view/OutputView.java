package lotto.view;

import static lotto.util.Constant.LOTTO_PROFIT_STANDARD;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.LottoPrize;
import lotto.domain.LottoResult;

public class OutputView {

    public static void writeErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
        System.out.println();
    }

    public static void writeLottoTickets(List<Lotto> lottoTickets) {
        System.out.printf("%d개를 구매했습니다.\n", lottoTickets.size());

        for (Lotto lottoTicket : lottoTickets) {
            Collections.sort(lottoTicket.getLotto());
            System.out.println(lottoTicket.getLotto());
        }
    }

    public static void writeLottoResult(LottoResult lottoResult) {
        System.out.println(formatProfitStatusHead());

        System.out.println(formatProfitStatusBody(lottoResult));

        System.out.println(formatProfitStatusTail(lottoResult));
    }

    private static String formatProfitStatusHead() {
        StringBuilder builder = new StringBuilder();

        builder.append("\n당첨 통계\n");
        builder.append("---------\n");

        return builder.toString();
    }

    private static String formatProfitStatusBody(LottoResult lottoResult) {
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<LottoPrize, Integer> entry : lottoResult.getLottoResult().entrySet()) {
            LottoPrize lottoPrize = entry.getKey();

            if (lottoPrize.equals(LottoPrize.MISS)) {
                continue;
            }
            builder.append(String.format("%d개 일치 (%d원) - %d개\n", lottoPrize.getHitNumbers(), lottoPrize.getPrize(),
                    entry.getValue()));
        }
        return builder.toString();
    }

    private static String formatProfitStatusTail(LottoResult lottoResult) {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format("총 수익률은 %.2f입니다.", lottoResult.getLottoProfitRate()));

        String profitStatus = formatProfitStatus(lottoResult.getLottoProfitRate());
        builder.append(String.format("(기준이 1이기 때문에 결과적으로 %s라는 의미임)", profitStatus));

        return builder.toString();
    }

    private static String formatProfitStatus(double profitRate) {
        if (profitRate < LOTTO_PROFIT_STANDARD) {
            return "손해";
        }
        return "이득";
    }
}
