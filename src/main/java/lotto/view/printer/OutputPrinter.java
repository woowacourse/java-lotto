package lotto.view.printer;

import static lotto.view.InputView.LOTTO_NUMBER_DELIMITER;

import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.LottoMatchType;
import lotto.domain.LottoTicket;

public class OutputPrinter {
    private static final String START_BRACKET = "[";
    private static final String END_BRACKET = "]";
    private static final String NEW_LINE = "\n";

    private OutputPrinter() {
    }

    public static void printCompletedPurchaseGuideMessage(int numberOfTicket) {
        System.out.printf("%d개를 구매했습니다." + NEW_LINE, numberOfTicket);
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

    public static void printEachNumberMatchedCountMessage(LottoMatchType lottoMatchType, Map<LottoMatchType, Integer> result) {
        System.out.printf(lottoMatchType.getMatchCountMessage() + NEW_LINE,
            result.get(lottoMatchType));
    }

    public static void printProfitMessage(double profit) {
        System.out.printf("총 수익률은 %.2f입니다." + NEW_LINE, profit);
    }
}
