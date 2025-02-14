package lotto.view;

import static lotto.util.Constant.LOTTO_PROFIT_STANDARD;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import lotto.domain.LottoPrize;
import lotto.domain.LottoResult;

public class OutputView {

    public static void writeErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
        System.out.println();
    }

    public static void writeLottoTickets(List<List<Integer>> lottoTickets) {
        System.out.printf("%d개를 구매했습니다.", lottoTickets.size());
        for (List<Integer> lottoTicket : lottoTickets) {
            Collections.sort(lottoTicket);
            System.out.println(lottoTicket);
        }
    }

    public static void writeLottoResult(LottoResult lottoResult) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n당첨 통계\n");
        sb.append("---------\n");

        for (Map.Entry<LottoPrize, Integer> entry : lottoResult.getLottoResult().entrySet()) {
            LottoPrize lottoPrize = entry.getKey();

            if (lottoPrize.equals(LottoPrize.MISS)) {
                continue;
            }
            sb.append(String.format("%d개 일치 (%d원) - %d개\n", lottoPrize.getHitNumbers(), lottoPrize.getPrize(),
                    entry.getValue()));
        }

        sb.append(String.format("총 수익률은 %.2f입니다.", lottoResult.getLottoProfitRate()));
        String profitStatus = formatProfitStatus(lottoResult.getLottoProfitRate());
        sb.append(String.format("(기준이 1이기 때문에 결과적으로 %s라는 의미임)", profitStatus));

        System.out.println(sb);
    }

    private static String formatProfitStatus(double profitRate) {
        if (profitRate < LOTTO_PROFIT_STANDARD) {
            return "손해";
        }
        return "이득";
    }
}
