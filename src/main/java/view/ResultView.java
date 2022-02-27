package view;

import domain.Rank;
import dto.WinningResultDto;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ResultView {
    public static final int NO_MATCH_RANK_INDEX_IN_REVERSED_RANK_VALUES = 0;
    public static final int PROFIT_CRITERIA = 1;
    public static final String MESSAGE_FOR_LOSS = "손해";
    public static final String MESSAGE_FOR_PROFIT = "이익";

    public static void printResult(WinningResultDto winningResultDto) {
        printTitle();
        printWinningResults(winningResultDto);
        printProfitRatio(winningResultDto.getProfitRatio());
    }

    private static void printTitle() {
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    private static void printWinningResults(WinningResultDto winningResultDto) {
        getRanks().forEach(rank -> {
            int winningCount = winningResultDto.getWinningCountByRank(rank).getCount();
            printSingleWinningResult(rank, winningCount);
        });
    }

    private static List<Rank> getRanks() {
        List<Rank> ranks = Arrays.stream(Rank.values())
                .collect(Collectors.toList());

        Collections.reverse(ranks);
        ranks.remove(NO_MATCH_RANK_INDEX_IN_REVERSED_RANK_VALUES);

        return ranks;
    }

    private static void printSingleWinningResult(Rank rank, int count) {
        System.out.println(
                rank.getSameNumberCount() + "개 일치" + getBonusBallTextByRank(rank)
                        + " (" + rank.getPrize() + "원) - " + count + "개"
        );
    }

    private static String getBonusBallTextByRank(Rank rank) {
        String bonusBall = "";
        if (rank == Rank.SECOND) {
            bonusBall = ", 보너스 볼 일치";
        }

        return bonusBall;
    }

    private static void printProfitRatio(double profitRatio) {
        System.out.println(
                "총 수익률은 " + profitRatio + "입니다.(기준이 1이기 때문에 결과적으로 "
                        + getProfitOrLossText(profitRatio) + "(이)라는 의미임)"
        );
    }

    private static String getProfitOrLossText(double profitRatio) {
        if (profitRatio < PROFIT_CRITERIA) {
            return MESSAGE_FOR_LOSS;
        }
        return MESSAGE_FOR_PROFIT;
    }


}

