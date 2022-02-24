package view;

import static model.LottoRank.*;

import java.util.List;
import java.util.stream.Collectors;
import model.LottoNumber;
import model.LottoRank;
import model.LottoTicket;
import model.Money;
import model.WinningStatistics;

public class OutputView {

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printPurchasedTickets(List<LottoTicket> lottoTickets) {
        System.out.printf("%d개를 구매했습니다.%n", lottoTickets.size());
        for (LottoTicket lottoTicket : lottoTickets) {
            printLottoNumbers(lottoTicket);
        }
    }

    private static void printLottoNumbers(LottoTicket lottoTicket) {
        List<String> lottoNumbers = lottoTicket.getLottoNumbers().stream()
                .map(LottoNumber::toString)
                .collect(Collectors.toUnmodifiableList());
        System.out.printf("[%s]%n", String.join(", ", lottoNumbers));
    }

    public static void printStatistics(WinningStatistics winningStatistics, Money inputMoney) {
        System.out.printf("당첨 통계%n---------%n");
        for (LottoRank lottoRank : values()) {
            System.out.println(getWinningResult(winningStatistics, lottoRank));
        }
        System.out.println(getEarningsRateResult(winningStatistics, inputMoney));
    }

    private static String getWinningResult(WinningStatistics winningStatistics, LottoRank lottoRank) {
        if (lottoRank == THIRD) {
            return String.format("%s개 일치, 보너스 볼 일치 (%s원) - %s개", THIRD.getWinningNumberCount(),
                    THIRD.getPrizeMoney(), winningStatistics.get(THIRD));
        }
        return String.format("%s개 일치 (%s원) - %s개", lottoRank.getWinningNumberCount(), lottoRank.getPrizeMoney(),
                winningStatistics.get(lottoRank));
    }

    private static String getEarningsRateResult(WinningStatistics winningStatistics, Money inputMoney) {
        double earningsRate = winningStatistics.getEarningsRate(inputMoney.getAmount());
        String result = String.format("총 수익률은 %.2f 입니다.", earningsRate);
        if (earningsRate >= 1) {
            return result;
        }
        return String.format("%s(기준이 1이기 때문에 결과적으로 손해라는 의미임)", result);
    }
}
