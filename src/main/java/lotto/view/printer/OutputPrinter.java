package lotto.view.printer;

import static lotto.view.InputView.LOTTO_NUMBER_DELIMITER;

import java.util.stream.Collectors;
import lotto.domain.LottoTicket;
import lotto.type.LottoMatchType;

public class OutputPrinter {
    private static final String NEW_LINE = System.lineSeparator();

    private OutputPrinter() {
    }

    public static void printCompletedPurchaseGuideMessage(int numberOfTicket) {
        System.out.printf("%d개를 구매했습니다." + NEW_LINE, numberOfTicket);
    }

    public static void printLottoTicketNumbers(LottoTicket lottoTicket) {
        System.out.println("[" +
            lottoTicket.getLottoTicketNumbers().stream()
                .map(lottoNumber -> Integer.toString(lottoNumber.getNumber()))
                .collect(Collectors.joining(LOTTO_NUMBER_DELIMITER))
            + "]"
        );
    }

    public static void printResultTitleMessage() {
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    public static void printEachNumberMatchedCountMessage(LottoMatchType lottoMatchType,
        int countOfMatchedNumbers) {
        System.out.printf(lottoMatchType.getMatchCountMessage() + NEW_LINE, countOfMatchedNumbers);
    }

    public static void printProfitMessage(double profit) {
        System.out.printf("총 수익률은 %.2f입니다." + NEW_LINE, profit);
    }
}
