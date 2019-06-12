package lotto.view;

import lotto.domain.Rank;
import lotto.domain.UserLottos;
import lotto.domain.ticket.Ticket;
import lotto.dto.LottoResultDto;

import java.util.Map;

public class OutputView {

    public static void printLottos(UserLottos userLottos) {
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
            stringBuilder.append(entry.getKey().toString()).append(" - ").append(entry.getValue()).append(" 개\n");
        }
        stringBuilder.append("총 수익률은 ").append(lottoResult.getSummury()).append("입니다.\n");
        System.out.println("당첨통계\n-------");
        System.out.println(stringBuilder.toString());
    }
}
