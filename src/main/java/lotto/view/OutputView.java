package lotto.view;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.LottoNumber;
import lotto.domain.LottoNumbers;
import lotto.domain.LottoTickets;

public class OutputView {
    private OutputView() {
    }

    public static void printTicketCount(int ticketCount) {
        System.out.println(ticketCount + "개를 구매했습니다.");
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
        return "[" + String.join(", ", list) + "]";
    }

    private static List<String> convertLottoNumberListToIntegerList(List<LottoNumber> lottoNumberList) {
        return lottoNumberList.stream()
                .map(LottoNumber::convertToString)
                .collect(Collectors.toList());
    }
}
