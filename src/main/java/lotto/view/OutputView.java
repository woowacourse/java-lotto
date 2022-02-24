package lotto.view;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.LottoNumber;
import lotto.domain.LottoNumbers;
import lotto.domain.LottoTickets;

public class OutputView {
    public static final String OPEN_BRACKET = "[";
    public static final String CLOSE_BRACKET = "]";
    public static final String BLANK = " ";
    public static final String DELIMITER = ",";
    public static final String TICKET_PURCHASE_SENTENCE = "개를 구매했습니다.";

    private OutputView() {
    }

    public static void printTicketCount(int ticketCount) {
        System.out.println(ticketCount + TICKET_PURCHASE_SENTENCE);
    }

    public static void printTicket(LottoTickets lottoTickets) {
        List<LottoNumbers> lottoNumbersList = lottoTickets.getLottoTickets();

        for (LottoNumbers lottoNumbers : lottoNumbersList) {
            List<LottoNumber> lottoNumberList = lottoNumbers.getLottoNumbers();
            String sentence = joinList(convertLottoNumberListToIntegerList(lottoNumberList));
            System.out.println(sentence);
        }
    }

    private static String joinList(List<String> list) {
        String str = String.join(DELIMITER + BLANK, list);
        return String.format("%s%s%s", OPEN_BRACKET, str, CLOSE_BRACKET);
    }

    private static List<String> convertLottoNumberListToIntegerList(List<LottoNumber> lottoNumberList) {
        return lottoNumberList.stream()
                .sorted(Comparator.comparingInt(LottoNumber::toInt))
                .map(lottoNumber -> Integer.toString(lottoNumber.toInt()))
                .collect(Collectors.toList());
    }
}
