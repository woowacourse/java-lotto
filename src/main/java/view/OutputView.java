package view;

import java.util.List;
import java.util.stream.Collectors;
import domain.dto.LottoTicketDto;
import domain.dto.WinningResultDto;

public class OutputView {
    private static final String PURCHASE_COUNT_MESSAGE = "수동으로 %d장, 자동으로 %d개를 구매했습니다.\n";
    private static final String RIGHT_LIST_COVER = "[";
    private static final String LEFT_LIST_COVER = "]";
    private static final String LIST_JOINING_DELIMITER = ", ";
    private static final String WINNING_STATISTICS_MESSAGE = "\n당첨 통계";
    private static final String DIVISION_LINE = "---------";
    private static final String RESULT_MESSAGE_PREFIX = "%d개 일치";
    private static final String MATCH_BONUS_NUMBER_MESSAGE = ", 보너스 볼 일치";
    private static final String RESULT_MESSAGE_SUFFIX = "(%d원)- %d개\n";
    private static final String TOTAL_RETURN_MESSAGE = "총 수익률은 %.2f입니다.\n";

    private static final OutputView instance = new OutputView();

    private OutputView() {
    }

    public void showLottoTicket(List<LottoTicketDto> lottoTickets, int selfPurchaseCount) {
        System.out.printf(PURCHASE_COUNT_MESSAGE, selfPurchaseCount, lottoTickets.size() - selfPurchaseCount);
        lottoTickets.forEach(lottoTicket -> System.out.println(toLottoTicketPrintForm(lottoTicket)));
    }

    public void showLottoResult(List<WinningResultDto> winningResults, double rateOfReturn) {
        System.out.println(WINNING_STATISTICS_MESSAGE);
        System.out.println(DIVISION_LINE);
        winningResults.forEach(this::printWinningResult);
        System.out.printf(TOTAL_RETURN_MESSAGE, rateOfReturn);
    }

    public static OutputView getInstance() {
        return instance;
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

    private String toLottoTicketPrintForm(LottoTicketDto lottoTicket) {
        return RIGHT_LIST_COVER + joiningLottoNumbers(lottoTicket) + LEFT_LIST_COVER;
    }

    private String joiningLottoNumbers(LottoTicketDto lottoTicket) {
        return lottoTicket.getLottoNumbers().stream()
                .map(Object::toString)
                .collect(Collectors.joining(LIST_JOINING_DELIMITER));
    }
}
