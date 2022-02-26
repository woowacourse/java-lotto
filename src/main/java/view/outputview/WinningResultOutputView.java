package view.outputview;

import java.util.List;
import model.WinningResultDto;

public class WinningResultOutputView implements OutputView<List<WinningResultDto>> {
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
    public void showOutputData(List<WinningResultDto> winningResults) {
        System.out.println(WINNING_STATISTICS_MESSAGE);
        System.out.println(DIVISION_LINE);
        winningResults.forEach(this::printWinningResult);
    }

    private void printWinningResult(WinningResultDto winningResult) {
        String winningMessage = makeWinningMessage(
                winningResult.getMatchCount(),
                winningResult.isMatchBonus(),
                winningResult.getPrizeMoney(),
                winningResult.getTicketCount()
        );
        System.out.println(winningMessage);
    }

    private String makeWinningMessage(int matchCount, boolean matchBonus, int prizeMoney, int ticketCount) {
        String winningMessage = matchCount + MATCH_COUNT_MESSAGE;
        if (matchBonus) {
            winningMessage += MATCH_BONUS_NUMBER_MESSAGE;
        }
        winningMessage += LEFT_PRICE_COVER + prizeMoney + MONEY_UNIT + RIGHT_PRICE_COVER + SEPARATOR
                + ticketCount + COUNTING_UNIT;
        return winningMessage;
    }
}
