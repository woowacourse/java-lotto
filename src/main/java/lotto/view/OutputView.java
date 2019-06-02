package lotto.view;

import lotto.domain.LottoTicket;
import lotto.domain.RankType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class OutputView {
    public static void printLottoTickets(final int amountOfCustoms, final int amountOfLottoTickets, final List<LottoTicket> lottoTickets) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", amountOfCustoms, amountOfLottoTickets - amountOfCustoms);

        for (LottoTicket lottoTicket : lottoTickets) {
            System.out.println(lottoTicket);
        }
    }

    public static void printResult(Map<RankType, Integer> countOfResult) {
        System.out.println("당첨 통계\n" + "---------");

        for (RankType rankType : RankType.values()) {
            int matchingCount = rankType.getMatchingCount();
            int prize = rankType.getPrize();
            int count = countOfResult.get(rankType);

            if (rankType.equals(RankType.SECOND)) {
                System.out.printf("%d개 일치, 보너스 볼 일치(%d원) - %d개\n", matchingCount, prize, count);
                continue;
            }
            if (rankType.equals(RankType.NOTHING)) {
                continue;
            }
            System.out.printf("%d개 일치 (%d원)- %d개\n", matchingCount, prize, count);
        }
    }
}
