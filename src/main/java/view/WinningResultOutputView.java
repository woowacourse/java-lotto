package view;

import java.util.Map;
import java.util.Map.Entry;

import model.WinningRank;

public class WinningResultOutputView implements OutputView<Map<WinningRank, Integer>> {

    private static final String WINNING_STATISTICS_MESSAGE = "당첨 통계";
    private static final String DIVISION_LINE = "---------";
    private static final String MATCH_COUNT_MESSAGE = "개 일치";
    private static final String MATCH_BONUS_NUMBER_MESSAGE = ", 보너스 볼 일치";
    private static final String LEFT_PRICE_COVER = "(";
    private static final String MONEY_UNIT = "원";
    private static final String RIGHT_PRICE_COVER = ")";
    private static final String SEPARATOR = "- ";
    private static final String COUNTING_UNIT = "개";

    @Override
    public void printOutputData(final Map<WinningRank, Integer> winningResult) {
        System.out.println(WINNING_STATISTICS_MESSAGE);
        System.out.println(DIVISION_LINE);
        winningResult.entrySet()
                .forEach(this::printWinningResult);
    }

    private void printWinningResult(final Entry<WinningRank, Integer> winningRankInfo) {
        WinningRank winningRank = winningRankInfo.getKey();
        if (winningRank == WinningRank.NONE) {
            return;
        }
        String winningMessage = makeWinningMessage(winningRankInfo, winningRank);
        System.out.println(winningMessage);
    }

    private String makeWinningMessage(final Entry<WinningRank, Integer> winningPrizeInfo,
                                      final WinningRank winningRank) {
        String winningMessage = winningRank.getMatchCount() + MATCH_COUNT_MESSAGE;
        if (winningRank == WinningRank.SECOND) {
            winningMessage += MATCH_BONUS_NUMBER_MESSAGE;
        }
        winningMessage += LEFT_PRICE_COVER + winningRank.getPrizeMoney() + MONEY_UNIT + RIGHT_PRICE_COVER + SEPARATOR
                + winningPrizeInfo.getValue() + COUNTING_UNIT;
        return winningMessage;
    }
}
