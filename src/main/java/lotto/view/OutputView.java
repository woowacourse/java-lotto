package lotto.view;

import java.util.List;
import java.util.Map;
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
        Map<LottoWinningResponse, Long> responseCountMap = statistics.getResponseCountMap();
        responseCountMap.entrySet()
            .forEach(entry -> client.output(formatWinningResponse(entry) + "\n"));
    }

    private String formatWinningResponse(Map.Entry<LottoWinningResponse, Long> entry) {
        String format = "%d개 일치 (%d원)- %d개";
        if (entry.getKey().isSecondPlace()) {
            format = "%d개 일치, 보너스 볼 일치(%d원) - %d개";
        }
        return String.format(format, entry.getKey().getMatchCount(), entry.getKey().getPrize(), entry.getValue());
    }

    private void outputProfit(LottoStatisticsResponse statistics) {
        client.output(String.format("총 수익률은 %.2f입니다.\n", calculateProfit(statistics)));
    }

    private double calculateProfit(LottoStatisticsResponse statistics) {
        Map<LottoWinningResponse, Long> rankCountMap = statistics.getResponseCountMap();
        int money = statistics.getMoney();
        long sum = rankCountMap.entrySet().stream()
            .mapToLong(x -> x.getKey().getPrize() * x.getValue())
            .sum();
        return (double) sum / money;
    }
}
