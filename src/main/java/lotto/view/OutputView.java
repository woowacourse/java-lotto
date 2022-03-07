package lotto.view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lotto.domain.LottoLine;
import lotto.domain.LottoNumber;
import lotto.domain.LottoRank;
import lotto.domain.LottoStatistics;
import lotto.domain.PurchaseResult;

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

    public void outputStatistics(LottoStatistics statistics) {
        outputPrompt();
        outputMatches(statistics);
        outputProfit(statistics);
    }

    private void outputPrompt() {
        System.out.print("당첨 통계\n");
        System.out.print("---------\n");
    }

    private void outputMatches(LottoStatistics statistics) {
        statistics.getRankCounts()
            .entrySet()
            .stream()
            .forEach(entry -> System.out.println(formatRank(entry)));
    }

    private String formatRank(Map.Entry<LottoRank, Integer> entry) {
        LottoRank rank = entry.getKey();
        int ticketCount = entry.getValue();

        String format = "%d개 일치 (%d원)- %d개";
        if (rank == LottoRank.SECOND) {
            format = "%d개 일치, 보너스 볼 일치(%d원) - %d개";
        }
        return String.format(format, rank.getMatchCount(), rank.getPrize(), ticketCount);
    }

    private void outputProfit(LottoStatistics statistics) {
        System.out.printf("총 수익률은 %.2f입니다.\n", statistics.getProfitRate());
    }
}
