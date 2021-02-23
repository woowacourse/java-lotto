package lotto.view.printer;

import static lotto.view.InputView.LOTTO_NUMBERS_DELIMITER;

import java.math.BigDecimal;
import java.util.stream.Collectors;
import lotto.domain.LottoTicket;
import lotto.domain.ticketpurchase.UserPurchase;
import lotto.type.LottoMatchType;

public class OutputPrinter {
    private static final String NEW_LINE = System.lineSeparator();

    private OutputPrinter() {
    }

    public static void printManualAndAutoTicketsSizeMessage(UserPurchase userPurchase) {
        System.out.printf("수동으로 %d장, 자동으로 %d개를 구매했습니다." + NEW_LINE,
            userPurchase.manualTicketsSize(), userPurchase.randomTicketsSize());
    }

    public static void printOneLottoTicketNumbers(LottoTicket lottoTicket) {
        System.out.println("[" +
            lottoTicket.getNumbers().stream()
                .map(lottoNumber -> Integer.toString(lottoNumber.getNumber()))
                .collect(Collectors.joining(LOTTO_NUMBERS_DELIMITER))
            + "]"
        );
    }

    public static void printResultTitle() {
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    public static void printLottoMatchTypeCountMessage(LottoMatchType lottoMatchType,
        int countOfMatchedNumbers) {
        System.out.printf(lottoMatchType.getMatchCountMessage() + NEW_LINE, countOfMatchedNumbers);
    }

    public static void printProfitMessage(BigDecimal profit) {
        System.out.printf("총 수익률은 %.2f입니다." + NEW_LINE, profit);
    }
}
