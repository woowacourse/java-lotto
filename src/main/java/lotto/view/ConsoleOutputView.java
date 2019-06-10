package lotto.view;

import lotto.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConsoleOutputView {
    public static void printAmount(LottoMoney lottoMoney) {
        System.out.println(lottoMoney.getAmount() + "개 구입했습니다.");
    }

    public static void printTickets(LottoTickets tickets) {
        List<LottoTicket> lottoTickets = tickets.getLottoTickets();
        StringBuilder result = new StringBuilder();
        for (LottoTicket lottoTicket : lottoTickets) {
            List<LottoNumber> lottoNumbers = new ArrayList<>(lottoTicket.getLottoNumbers());
            result.append(lottoNumbers + "\n");
        }
        System.out.println(result.toString());
    }

    public static void printResults(LottoResults lottoResults) {
        System.out.println("\n당첨 통계\n---------");
        printStatistics(lottoResults);
        printYield(lottoResults);
    }

    private static void printStatistics(LottoResults lottoResults) {
        for (Map.Entry<LottoRank, Integer> lottoRankIntegerEntry : lottoResults.getLottoRewards().entrySet()) {
            printStatistic(lottoRankIntegerEntry);
        }
    }

    private static void printStatistic(Map.Entry<LottoRank, Integer> lottoRankIntegerEntry) {
        LottoRank lottoRank = lottoRankIntegerEntry.getKey();
        int amount = lottoRankIntegerEntry.getValue();
        if (lottoRank.equals(LottoRank.MISS)) {
            return;
        }
        if (lottoRank.equals(LottoRank.SECOND)) {
            System.out.println(String.format("%d개 일치, 보너스 볼 일치(%d) %d개", lottoRank.getCountOfMatch(), lottoRank.getRewardMoney(), amount));
            return;
        }
        System.out.println(String.format("%d개 일치, (%d) %d개", lottoRank.getCountOfMatch(), lottoRank.getRewardMoney(), amount));
    }

    private static void printYield(LottoResults lottoResults) {
        System.out.println(String.format("총 수익률은 : %.3f%s 입니다.", lottoResults.getYield(), "%"));
    }

    public static void printAmounts(int manualAmount, LottoMoney lottoMoney) {
        System.out.println(String.format("수동으로 %d장, 자동으로 %d장 구매했습니다.", manualAmount, (lottoMoney.getAmount() - manualAmount)));
    }
}
