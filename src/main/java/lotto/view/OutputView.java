package lotto.view;

import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.LottoPrize;
import lotto.domain.LottoResult;
import lotto.domain.LottoTickets;

public class OutputView {

    public static void writeErrorMessage(String message) {
        System.out.println(message);
    }

    public static void writeLottoTickets(LottoTickets lottoTickets) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d개를 구매했습니다.\n", lottoTickets.getLottoTicketsCount()));
        for (Lotto lottoTicket : lottoTickets.getLottoTickets()) {
            sb.append(lottoTicket.getLottoNumbers()).append('\n');
        }
        System.out.println(sb);
    }

    public static void writeLottoResult(LottoResult lottoResult) {
        StringBuilder sb = new StringBuilder();
        writeLottoResultHeader(sb);
        for (Map.Entry<LottoPrize, Integer> entry : lottoResult.getLottoResult().entrySet()) {
            writeLottoResultHit(sb, entry);
        }
        System.out.print(sb);
    }

    public static void writeLottoResultProfitRate(double profitRate) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("총 수익률은 %.2f입니다.", profitRate));
        String profitStatus = formatProfitStatus(profitRate);
        sb.append(String.format("(기준이 1이기 때문에 결과적으로 %s라는 의미임)", profitStatus));
        System.out.println(sb);
    }

    private static void writeLottoResultHeader(StringBuilder sb) {
        sb.append("\n당첨 통계\n");
        sb.append("---------\n");
    }

    private static void writeLottoResultHit(StringBuilder sb, Map.Entry<LottoPrize, Integer> entry) {
        LottoPrize lottoPrize = entry.getKey();
        if (lottoPrize.equals(LottoPrize.MISS)) {
            return;
        }
        sb.append(String.format("%d개 일치 (%d원) - %d개\n", lottoPrize.getHitNumbers(), lottoPrize.getPrize(),
                entry.getValue()));
    }

    private static String formatProfitStatus(double profitRate) {
        if (profitRate < 1) {
            return "손해";
        }
        return "이득";
    }
}
