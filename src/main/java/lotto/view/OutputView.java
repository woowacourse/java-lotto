package lotto.view;

import java.util.List;
import java.util.stream.Collectors;

import lotto.client.OutputClient;
import lotto.dto.LottoStatisticsResponse;
import lotto.dto.LottoTicketResponse;
import lotto.dto.LottoWinningResponse;

public class OutputView {

    private final OutputClient client;

    public OutputView(OutputClient client) {
        this.client = client;
    }

    public void outputTickets(List<LottoTicketResponse> responses) {
        client.output(String.format("%d개를 구매했습니다.\n", responses.size()));
        responses.forEach(this::outputTicket);
        client.output("");
    }

    private void outputTicket(LottoTicketResponse response) {
        client.output(sortTicket(response).toString() + "\n");
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
        client.output("당첨 통계\n");
        client.output("---------\n");
    }

    private void outputMatches(LottoStatisticsResponse statistics) {
        List<LottoWinningResponse> responses = statistics.getWinningResponses();
        responses.stream()
            .sorted()
            .forEach(response -> client.output(formatWinningResponse(response) + "\n"));
    }

    private String formatWinningResponse(LottoWinningResponse response) {
        String format = "%d개 일치 (%d원)- %d개";
        if (response.isSecondPlace()) {
            format = "%d개 일치, 보너스 볼 일치(%d원) - %d개";
        }
        return String.format(format, response.getMatchCount(), response.getPrize(), response.getTicketCount());
    }

    private void outputProfit(LottoStatisticsResponse statistics) {
        client.output(String.format("총 수익률은 %.2f입니다.\n", calculateProfit(statistics)));
    }

    private double calculateProfit(LottoStatisticsResponse statistics) {
        List<LottoWinningResponse> responses = statistics.getWinningResponses();
        int money = statistics.getMoney();
        long sum = responses.stream()
            .mapToLong(this::calculateProfitPerWinning)
            .sum();
        return (double)sum / money;
    }

    private long calculateProfitPerWinning(LottoWinningResponse response) {
        return (long) response.getPrize() * response.getTicketCount();
    }
}
