package lotto.view;

import static lotto.domain.MatchRank.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import lotto.domain.LottoNumber;
import lotto.domain.LottoStatistics;
import lotto.domain.MatchRank;
import lotto.domain.Profit;
import lotto.dto.LottoDto;
import lotto.dto.WalletDto;

public class OutputView {

    public void print(String output) {
        System.out.println(output);
    }

    public void printResult(LottoStatistics statistics, Profit profit) {
        printStatics(statistics);
        printProfit(profit);
    }

    public void printWallet(WalletDto walletDto) {
        List<LottoDto> lottoDtos = walletDto.lottoDtos();

        StringBuilder sb = new StringBuilder();
        for (LottoDto dto : lottoDtos) {
            List<LottoNumber> numbers = new ArrayList<>(dto.numbers());
            Collections.sort(numbers);
            sb.append(numbers);
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private void printStatics(LottoStatistics statistics) {
        Map<MatchRank, Integer> rankCounts = statistics.getRankCounts();
        System.out.println("\n당첨 통계");
        System.out.println("---------");

        StringBuilder output = new StringBuilder();

        for (MatchRank rank : values()) {
            if (rank == NO_MATCH) {
                continue;
            }

            output.append(getSingleRankOutput(rank, rankCounts));
        }

        System.out.println(output);
    }

    private String getSingleRankOutput(MatchRank rank, Map<MatchRank, Integer> rankCounts) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d개 일치", rank.getNumber()));
        if (rank == MATCH_BONUS) {
            sb.append(", 보너스볼 일치");
        }
        sb.append(String.format("(%d원)", rank.getMoney()));
        int count = rankCounts.get(rank);
        sb.append(String.format(" - %d개\n", count));

        return sb.toString();
    }

    private void printProfit(Profit profit) {
        String print = "총 수익률은 " + String.format("%.2f", profit.rate()) + "입니다.";
        if (!profit.isProfit()) {
            print += "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
        }

        System.out.println(print);
    }

    public void printLottoCount(int purchasedLottoCount) {
        System.out.println(purchasedLottoCount + "개를 구매했습니다.\n");
    }
}
