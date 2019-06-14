package lotto.view;

import lotto.domain.Rank;
import lotto.domain.Ticket;
import lotto.dto.LottoResultDto;

import java.util.Map;

public class OutputView {

    public static void printLottos(UserTickets userLottos) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("로또를 ").append(userLottos.tickets().size()).append(" 개 구입했습니다.\n");
        for (Ticket ticket : userLottos.tickets()) {
            stringBuilder.append(ticket).append("\n");
        }
        System.out.println(stringBuilder.toString());
    }

    public static void printResult(LottoResultDto lottoResult) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Rank, Integer> entry : lottoResult.getResults().entrySet()) {
            stringBuilder.append(rankToString(entry.getKey())).append(" - ").append(entry.getValue()).append(" 개\n");
        }
        stringBuilder.append("총 수익률은 ").append(lottoResult.getSummary()).append("입니다.\n");
        System.out.println("당첨통계\n-------");
        System.out.println(stringBuilder.toString());
    }

    private static String rankToString(Rank rank) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(rank.matchCount()).append(" 개 일치 ");
        if (rank.hasBonus()) {
            stringBuilder.append(" 보너스 볼 일치 ");
        }
        stringBuilder.append(rank.money()).append("원");
        return stringBuilder.toString();
    }
}
