package lotto.view;

import lotto.domain.*;

import java.util.ArrayList;
import java.util.List;

public class ConsoleOutputView {
    public static void printAmount(LottoMoney lottoMoney) {
        System.out.println(lottoMoney.getAmount() + "개 구입했습니다.");
    }

    public static void printTickets(List<LottoTicket> lottoTickets) {
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
        lottoResults.getLottoRewards().forEach((k, v) -> {
            if (k != LottoRank.MISS) {
                System.out.println(k.getCountOfMatch() + " 개 일치 " + k.getRewardMoney() + "  - " + v + "개");
            }
        });
    }

    private static void printYield(LottoResults lottoResults) {
        System.out.println("총 수익률은 : " + lottoResults.getYield() + "% 입니다.");
    }

    public static void printAmounts(int manualAmount, LottoMoney lottoMoney) {
        System.out.println("수동으로 : " + manualAmount + "개 자동으로 : " + (lottoMoney.getAmount() - manualAmount) + "개 구입하셨습니다.");
    }
}
