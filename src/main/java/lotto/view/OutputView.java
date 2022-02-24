package lotto.view;

import static java.util.stream.Collectors.joining;

import java.util.List;
import java.util.Map;
import lotto.controller.dto.LottoTicketDto;
import lotto.controller.dto.LottoTicketsDto;
import lotto.domain.Rank;

public class OutputView {

    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";
    private static final String TOTAL_COUNT_SUFFIX = "개를 구매했습니다.";
    private static final String TICKETS_INFO_DELIMITER = "\n";
    private static final String LOTTO_RESULT_MESSAGE = "\n당첨 통계\n---------";
    private static final long DEFAULT_COUNT = 0L;
    private static final String FIFTH_RESULT_MESSAGE = "3개 일치 (5000원)- %d개\n";
    private static final String FORTH_RESULT_MESSAGE = "4개 일치 (50000원)- %d개\n";
    private static final String THIRD_RESULT_MESSAGE = "5개 일치 (1500000원)- %d개\n";
    private static final String SECOND_RESULT_MESSAGE = "5개 일치, 보너스 볼 일치(30000000원)- %d개\n";
    private static final String FIRST_RESULT_MESSAGE = "6개 일치 (2000000000원)- %d개\n";
    private static final String TOTAL_YIELD_MESSAGE = "총 수익률은 %.2f 입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)";

    public void printErrorMessage(String errorMessage) {
        System.out.println(ERROR_MESSAGE_PREFIX + errorMessage);
        System.out.println();
    }

    public void printTotalCount(int totalCount) {
        System.out.println(totalCount + TOTAL_COUNT_SUFFIX);
    }

    public void printLottoTicketsInfo(LottoTicketsDto lottoTickets) {
        System.out.println(getTicketsInfo(lottoTickets.getLottoTickets()));
    }

    private String getTicketsInfo(List<LottoTicketDto> lottoTickets) {
        return lottoTickets.stream()
                .map(LottoTicketDto::getLottoNumbers)
                .map(Object::toString)
                .collect(joining(TICKETS_INFO_DELIMITER));
    }

    public void printLottoResultMessage() {
        System.out.println(LOTTO_RESULT_MESSAGE);
    }

    public void printYield(Map<Rank, Long> ranks, double yield) {
        System.out.printf(FIFTH_RESULT_MESSAGE, ranks.getOrDefault(Rank.FIFTH, DEFAULT_COUNT));
        System.out.printf(FORTH_RESULT_MESSAGE, ranks.getOrDefault(Rank.FORTH, DEFAULT_COUNT));
        System.out.printf(THIRD_RESULT_MESSAGE, ranks.getOrDefault(Rank.THIRD, DEFAULT_COUNT));
        System.out.printf(SECOND_RESULT_MESSAGE, ranks.getOrDefault(Rank.SECOND, DEFAULT_COUNT));
        System.out.printf(FIRST_RESULT_MESSAGE, ranks.getOrDefault(Rank.FIRST, DEFAULT_COUNT));
        System.out.printf(TOTAL_YIELD_MESSAGE, yield);
    }
}
