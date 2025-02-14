package view;

import domain.Rank;
import domain.dto.LottoResultViewDto;
import domain.dto.LottosViewDto;
import domain.dto.TicketViewDto;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String PURCHASE_RESULT = "%s개를 구매헀습니다.";
    private static final String WINNING_STATISTIC_MESSAGE = "당첨 통계";
    private static final String HYPHEN = "-";
    private static final String WINNING_RESULT = "%s개";
    private static final String RATE_MESSAGE = "총 수익률은 %,.2f입니다.";

    private OutputView() {
    }

    public static OutputView create() {
        return new OutputView();
    }

    public void printPurchaseResult(TicketViewDto ticketViewDto) {
        System.out.printf(PURCHASE_RESULT, ticketViewDto.getTicket());
        changeLine();
    }

    public void printLottos(LottosViewDto lottosViewDto) {
        List<List<Integer>> lottos = lottosViewDto.getLottos();
        for (List<Integer> lotto : lottos) {
            System.out.println(lotto);
        }
    }

    public void printWinningStatistic(LottoResultViewDto lottoResultViewDto) {
        printMessage(WINNING_STATISTIC_MESSAGE);
        printMessage(HYPHEN.repeat(9));
        Map<Rank, Integer> viewResult = lottoResultViewDto.getViewResult();
        for (Rank rank : viewResult.keySet()) {
            if (!rank.getDescription().isBlank()) {
                System.out.print(rank.getDescription() + " " + HYPHEN + " ");
                System.out.printf(WINNING_RESULT, viewResult.get(rank));
                changeLine();
            }
        }
    }

    public void printProfit(double profit) {
        System.out.printf(RATE_MESSAGE, profit);
        changeLine();
    }

    private void printMessage(String message) {
        System.out.println(message);
    }

    private void changeLine() {
        System.out.println();
    }
}
