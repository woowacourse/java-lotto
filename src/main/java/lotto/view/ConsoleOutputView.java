package lotto.view;

import lotto.domain.*;

import java.util.ArrayList;
import java.util.List;

public class ConsoleOutputView {
    public static void printAmount(LottoMoney lottoMoney) {
        System.out.println(lottoMoney.getAmount() + "개 구입했습니다.");
    }

    public static void printTickets(LottoTickets lottoTickets) {
        StringBuilder result = new StringBuilder();
        List<LottoTicket> lottoTicketList = lottoTickets.getLottoTickets();
        for (LottoTicket lottoTicket : lottoTicketList) {
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
            System.out.println(k.getCountOfMatch() + " 개 일치 " + k.getRewardMoney() + "  - " + v + "개");
        });
    }

    private static void printYield(LottoResults lottoResults) {
        System.out.println("총 수익률은 : " + lottoResults.getYield() + "% 입니다.");
    }
}
