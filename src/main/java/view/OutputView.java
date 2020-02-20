package view;

import domain.LottoProfit;
import domain.LottoResult;
import domain.Money;
import domain.numberscontainer.Ticket;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class OutputView {

    public static void printNumberOfTickets(int size) {
        System.out.println(size + "개를 구매했습니다.");
    }

    public static void printTickets(List<Ticket> tickets) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Ticket ticket : tickets) {
            stringBuilder.append(ticket.toString());
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder.toString());
    }

    public static void printLottoResults(Map<LottoResult, Long> lottoResults) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("당첨 통계\n-------------\n");
        List<LottoResult> results = Arrays.asList(LottoResult.values()).subList(1, LottoResult.values().length);
        results.stream()
                .forEach(result -> {
                    stringBuilder.append(result.toString());
                    stringBuilder.append(convertNullToZero(lottoResults.get(result)));
                    stringBuilder.append("개\n");
                });
        System.out.println(stringBuilder.toString());
    }

    private static long convertNullToZero(Long number) {
        if (number == null) return 0;
        return number;
    }

    public static void printProfit(LottoProfit profit) {
        System.out.println("총 수익률은 " + profit.getProfit() + "%입니다.");
    }
}