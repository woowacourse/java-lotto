package view;

import java.util.List;
import java.util.stream.Collectors;
import model.LottoNumber;
import model.LottoTicket;
import model.WinningStatistics;

public class OutputView {

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printPurchasedTickets(List<LottoTicket> lottoTickets) {
        System.out.printf("%d개를 구매했습니다.%n", lottoTickets.size());
        for (LottoTicket lottoTicket : lottoTickets) {
            printLottoNumbers(lottoTicket);
        }
    }

    private static void printLottoNumbers(LottoTicket lottoTicket) {
        List<String> lottoNumbers = lottoTicket.getLottoNumbers().stream()
                .map(LottoNumber::toString)
                .collect(Collectors.toUnmodifiableList());
        System.out.printf("[%s]%n", String.join(", ", lottoNumbers));
    }

    public static void printStatistics(WinningStatistics winningStatistics) {
        /**
         * 당첨 통계
         * ---------
         */
        System.out.println("");
    }
}
