package lotto.view;

import static java.lang.System.out;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.LottoRank;
import lotto.domain.PurchaseAmount;
import lotto.domain.WinningStats;
import lotto.domain.lottonumber.LottoTicket;

public class OutputView {

    public static void printErrorMessage(String message) {
        out.println(message);
    }

    public static void printPurchasedTickets(List<LottoTicket> lottoTickets) {
        out.printf("%d개를 구매했습니다.%n", lottoTickets.size());
        lottoTickets.forEach(OutputView::printLottoNumbers);
    }

    public static void printStatistics(WinningStats winningStats, PurchaseAmount purchaseAmount) {
        out.printf("당첨 통계%n---------%n");
        List<LottoRank> targetLottoRanks = getLottoRanksToPrint();
        for (LottoRank lottoRank : targetLottoRanks) {
            printWinningResult(winningStats, lottoRank);
        }
        printEarningsResult(winningStats, purchaseAmount);
    }

    private static void printLottoNumbers(LottoTicket lottoTicket) {
        List<String> lottoNumbers = lottoTicket.lottoNumbers().stream()
                .map(LottoNumber::toString)
                .collect(Collectors.toUnmodifiableList());
        out.printf("[%s]%n", String.join(", ", lottoNumbers));
    }

    private static void printWinningResult(WinningStats winningStats, LottoRank lottoRank) {
        if (lottoRank == LottoRank.THIRD) {
            out.printf("%s개 일치, 보너스 볼 일치 (%s원) - %s개%n", LottoRank.THIRD.getWinningNumberMatchCount(),
                    LottoRank.THIRD.getPrizeMoney(), winningStats.get(LottoRank.THIRD));
            return;
        }
        out.printf("%s개 일치 (%s원) - %s개%n", lottoRank.getWinningNumberMatchCount(), lottoRank.getPrizeMoney(),
                winningStats.get(lottoRank));
    }

    private static void printEarningsResult(WinningStats winningStats, PurchaseAmount purchaseAmount) {
        double earningsRate = winningStats.getEarningsRate(purchaseAmount);
        String result = String.format("총 수익률은 %.2f 입니다.", earningsRate);
        if (earningsRate < 1) {
            result = String.format("%s(기준이 1이기 때문에 결과적으로 손해라는 의미임)", result);
        }
        out.println(result);
    }

    private static List<LottoRank> getLottoRanksToPrint() {
        return Arrays.stream(LottoRank.values())
                .filter((LottoRank lottoRank) -> lottoRank != LottoRank.FAILED)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }
}
