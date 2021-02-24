package lotto.view.printer;

import lotto.domain.LottoTicket;
import lotto.domain.ticketresult.Rank;

import java.util.Map;
import java.util.stream.Collectors;

import static lotto.view.InputView.LOTTO_NUMBER_DELIMITER;

public class OutputPrinter {
    private static final String START_BRACKET = "[";
    private static final String END_BRACKET = "]";
    private static final String NEW_LINE = "\n";
    private static final String MATCH_MESSAGE = "%d개 일치 (%d원) - %d개";

    private OutputPrinter() {
    }

    public static void printCompletedPurchaseGuideMessage(int manualTicketCount, int autoTicketCount) {
        System.out.println(String.format("수동으로 %d장, 자동으로 %d장을 구매했습니다.", manualTicketCount, autoTicketCount));
        System.out.println();
    }

    public static void printLottoTicketNumbers(LottoTicket lottoTicket) {
        System.out.println(START_BRACKET +
                lottoTicket.getLottoTicketNumbers().stream()
                        .map(lottoNumber -> Integer.toString(lottoNumber.getNumber()))
                        .collect(Collectors.joining(LOTTO_NUMBER_DELIMITER))
                + END_BRACKET
        );
    }

    public static void printResultTitleMessage() {
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    public static void printEachNumberMatchedCountMessage(Rank rank, Map<Rank, Integer> result) {
        if (rank != Rank.NONE) {
            System.out.printf(MATCH_MESSAGE + NEW_LINE, rank.getCountMatchedNumbers(), rank.getPrizeMoney(), result.get(rank));
        }
    }

    public static void printProfitMessage(double profit) {
        System.out.printf("총 수익률은 %.2f입니다." + NEW_LINE, profit);
    }
}
