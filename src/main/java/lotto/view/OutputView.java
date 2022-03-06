package lotto.view;

import java.util.List;
import java.util.stream.Collectors;

import lotto.dto.LottoStatisticsResponse;
import lotto.dto.LottoTicketResponse;
import lotto.dto.LottoWinningResponse;

public class OutputView {

    private OutputView() {
    }

    private static class OutputViewHelper {
        private static final OutputView INSTANCE = new OutputView();
    }

    public static OutputView getInstance() {
        return OutputViewHelper.INSTANCE;
    }

    public void outputError(Exception exception) {
        System.out.println(exception.getMessage());
    }

    public void outputTickets(List<LottoTicketResponse> manualResponses, List<LottoTicketResponse> autoResponses) {
        System.out.printf(
            "수동으로 %d개, 자동으로 %d개를 구매했습니다.\n", manualResponses.size(), autoResponses.size()
        );
        manualResponses.forEach(this::outputTicket);
        autoResponses.forEach(this::outputTicket);
    }

    private void outputTicket(LottoTicketResponse response) {
        System.out.print(sortTicket(response).toString() + "\n");
    }

    private List<Integer> sortTicket(LottoTicketResponse response) {
        return response.getNumbers().stream()
            .sorted()
            .collect(Collectors.toList());
    }

    public void outputStatistics(LottoStatisticsResponse statistics) {
        outputPrompt();
        outputMatches(statistics);
        outputProfit(statistics);
    }

    private void outputPrompt() {
        System.out.print("당첨 통계\n");
        System.out.print("---------\n");
    }

    private void outputMatches(LottoStatisticsResponse statistics) {
        List<LottoWinningResponse> responses = statistics.getWinningResponses();
        responses.stream()
            .sorted()
            .forEach(response -> System.out.print(formatWinningResponse(response) + "\n"));
    }

    private String formatWinningResponse(LottoWinningResponse response) {
        String format = "%d개 일치 (%d원)- %d개";
        if (response.isSecondPlace()) {
            format = "%d개 일치, 보너스 볼 일치(%d원) - %d개";
        }
        return String.format(format, response.getMatchCount(), response.getPrize(), response.getTicketCount());
    }

    private void outputProfit(LottoStatisticsResponse statistics) {
        System.out.printf("총 수익률은 %.2f입니다.\n", statistics.getProfitRate());
    }
}
