package lotto.view;

import lotto.view.dto.LottoTicketResponseDTO;
import lotto.view.dto.PrizeResponseBundleDTO;

import java.util.List;

public class OutputView {
    private static final String MESSAGE_FOR_BONUS_CASE = "%d개 일치, 보너스 볼 일치(%d원) - %d개";
    private static final String MESSAGE_FOR_DEFAULT_CASE = "%d개 일치 (%d원)- %d개";
    private static final String SECOND = "SECOND";
    private static final String RATE_MESSAGE = "총 수익률은 %s입니다.";
    private static final String RESULT_HEADER = "당첨 통계\n-----------";
    private static final String LOTTO_NUMBERS_FORMAT = "[ %d %d %d %d %d %d ]";
    private static final String BUY_LOTTO_TICKET_COUNT_MESSAGE = "%d개를 구매하였습니다.";

    public static void printBuyTicketCount(int count) {
        System.out.println(String.format(BUY_LOTTO_TICKET_COUNT_MESSAGE, count));
    }

    public static void printBuyTickets(List<LottoTicketResponseDTO> lottoTicketResponseDTOs) {
        for (LottoTicketResponseDTO lottoTicketResponseDTO : lottoTicketResponseDTOs) {
            System.out.println(String.format(LOTTO_NUMBERS_FORMAT, lottoTicketResponseDTO.getNumbers()));
        }
    }

    public static void printResult(PrizeResponseBundleDTO prizeResponseBundleDTO) {
        System.out.println(RESULT_HEADER);
        printStatistics(prizeResponseBundleDTO);
        System.out.println(String.format(RATE_MESSAGE, prizeResponseBundleDTO.getRate()));
    }

    private static void printStatistics(PrizeResponseBundleDTO prizeResponseBundleDTO) {
        for (int i = 0; i < prizeResponseBundleDTO.size(); i++) {
            int matchCount = prizeResponseBundleDTO.getMatchCount(i);
            int defaultPrize = prizeResponseBundleDTO.getDefaultPrize(i);
            int matchTicketCount = prizeResponseBundleDTO.getMatchTicketCount(i);
            String message = String.format(findMessage(prizeResponseBundleDTO.getName(i)), matchCount, defaultPrize, matchTicketCount);
            System.out.println(message);
        }
    }

    private static String findMessage(String name) {
        if (isSecond(name)) {
            return MESSAGE_FOR_BONUS_CASE;
        }
        return MESSAGE_FOR_DEFAULT_CASE;
    }

    private static boolean isSecond(String name) {
        return SECOND.equals(name);
    }
}
