package view.outputview;

import java.util.List;
import model.WinningResultDto;

public class WinningResultOutputView implements OutputView<List<WinningResultDto>> {
    private static final String WINNING_STATISTICS_MESSAGE = "\n당첨 통계";
    private static final String DIVISION_LINE = "---------";
    private static final String RESULT_MESSAGE_PREFIX = "%d개 일치";
    private static final String MATCH_BONUS_NUMBER_MESSAGE = ", 보너스 볼 일치";
    private static final String RESULT_MESSAGE_SUFFIX = "(%d원)- %d개\n";

    @Override
    public void showOutputData(List<WinningResultDto> winningResults) {
        System.out.println(WINNING_STATISTICS_MESSAGE);
        System.out.println(DIVISION_LINE);
        winningResults.forEach(this::printWinningResult);
    }

    private void printWinningResult(WinningResultDto winningResult) {
        String winningMessage = makeWinningMessage(winningResult.isMatchBonus());
        System.out.printf(winningMessage,
                winningResult.getMatchCount(),
                winningResult.getPrizeMoney(),
                winningResult.getTicketCount()
        );
    }

    private String makeWinningMessage(boolean matchBonus) {
        StringBuilder stringBuilder = new StringBuilder(RESULT_MESSAGE_PREFIX);
        if (matchBonus) {
            stringBuilder.append(MATCH_BONUS_NUMBER_MESSAGE);
        }
        stringBuilder.append(RESULT_MESSAGE_SUFFIX);
        return stringBuilder.toString();
    }
}
