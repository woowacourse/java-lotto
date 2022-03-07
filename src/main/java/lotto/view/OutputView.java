package lotto.view;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.LottoLine;
import lotto.domain.LottoNumber;
import lotto.dto.LottoStatisticsResponse;
import lotto.dto.LottoTicketResponse;
import lotto.dto.LottoWinningResponse;
import lotto.dto.PurchaseResult;

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

    public void outputPurchaseResult(PurchaseResult purchaseResult) {
        int manualSize = purchaseResult.getManualTicket()
            .getLines()
            .size();
        int autoSize = purchaseResult.getAutoTicket()
            .getLines()
            .size();
        outputPurchaseSize(manualSize, autoSize);
        purchaseResult.getManualTicket()
            .getLines()
            .forEach(this::outputLine);
        purchaseResult.getAutoTicket()
            .getLines()
            .forEach(this::outputLine);
    }

    private void outputPurchaseSize(int manualSize, int autoSize) {
        System.out.printf(
            "수동으로 %d개, 자동으로 %d개를 구매했습니다.\n", manualSize, autoSize
        );
    }

    private void outputLine(LottoLine lottoLine) {
        System.out.println(
            sortLine(toInts(lottoLine))
        );
    }

    private List<Integer> toInts(LottoLine lottoLine) {
        return lottoLine.getNumbers()
            .stream()
            .map(LottoNumber::getNumber)
            .collect(Collectors.toList());
    }

    private List<Integer> sortLine(List<Integer> ints) {
        return ints.stream()
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
