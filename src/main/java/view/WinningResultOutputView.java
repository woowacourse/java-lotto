package view;

import java.util.Map;
import java.util.Map.Entry;
import model.WinningPrize;

public class WinningResultOutputView implements OutputView<Map<WinningPrize, Integer>> {

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
    public void printOutputData(Map<WinningPrize, Integer> winningResult) {
        System.out.println(WINNING_STATISTICS_MESSAGE);
        System.out.println(DIVISION_LINE);
        winningResult.entrySet()
                .forEach(this::printWinningResult);
    }

    private void printWinningResult(Entry<WinningPrize, Integer> winningPrizeInfo) {
        WinningPrize winningPrize = winningPrizeInfo.getKey();
        String winningMessage = makeWinningMessage(winningPrizeInfo, winningPrize);
        System.out.println(winningMessage);
    }

    private String makeWinningMessage(Entry<WinningPrize, Integer> winningPrizeInfo, WinningPrize winningPrize) {
        String winningMessage = winningPrize.getMatchCount() + MATCH_COUNT_MESSAGE;
        if (winningPrize.isMatchBonus()) {
            winningMessage += MATCH_BONUS_NUMBER_MESSAGE;
        }
        winningMessage += LEFT_PRICE_COVER + winningPrize.getPrizeMoney() + MONEY_UNIT + RIGHT_PRICE_COVER + SEPARATOR
                + winningPrizeInfo.getValue() + COUNTING_UNIT;
        return winningMessage;
    }
}
