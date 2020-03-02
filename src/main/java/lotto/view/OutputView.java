package lotto.view;

import lotto.view.dto.LottoTicketDTO;
import lotto.view.dto.PurchaseStatusDTO;
import lotto.view.dto.ResultDTO;
import lotto.view.dto.StatisticsDTO;

import java.util.List;

public class OutputView {
    private static final String MESSAGE_FOR_PURCHASE_STATUS = "\n수동으로 %d장, 자동으로 %d개를 구매했습니다.";
    private static final String MESSAGE_FOR_LOTTO_TICKET_INFO = "[%d, %d, %d, %d, %d, %d]";
    private static final String MESSAGE_FOR_RESULT_ANNOUNCE = "당첨 통계\n---------";
    private static final String MESSAGE_FOR_DEFAULT_CASE = "%d개 일치 (%d원)- %d개";
    private static final String MESSAGE_FOR_BONUS_CASE = "%d개 일치, 보너스 볼 일치(%d원) - %d개";
    private static final String SECOND = "SECOND";
    private static final String MESSAGE_FOR_RETURN_RATE = "총 수익률은 %.1f입니다.";

    public static void printPurchaseStatus(PurchaseStatusDTO purchaseStatusDTO) {
        System.out.println(String.format(MESSAGE_FOR_PURCHASE_STATUS, purchaseStatusDTO.getNumberOfManualTickets(),
                purchaseStatusDTO.getNumberOfAutoTickets()));
    }

    public static void printLottoTickets(List<LottoTicketDTO> lottoTicketDTOS) {
        for (LottoTicketDTO dto : lottoTicketDTOS) {
            System.out.println(String.format(MESSAGE_FOR_LOTTO_TICKET_INFO, dto.getNumbers().toArray()));
        }
        System.out.println();
    }

    public static void printResult(List<ResultDTO> resultDTOS, StatisticsDTO statisticsDTO) {
        System.out.println(MESSAGE_FOR_RESULT_ANNOUNCE);
        for (ResultDTO resultDTO : resultDTOS) {
            int numberOfMatch = resultDTO.getNumberOfMatch();
            int prize = resultDTO.getPrize();
            int numberOfMatchTickets = resultDTO.getNumberOfMatchTickets();
            String message = String.format(findMessage(resultDTO.getName()), numberOfMatch, prize, numberOfMatchTickets);
            System.out.println(message);
        }
        System.out.println(String.format(MESSAGE_FOR_RETURN_RATE, statisticsDTO.getRate()));
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
