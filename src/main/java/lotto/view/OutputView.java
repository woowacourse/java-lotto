package lotto.view;

import static java.util.stream.Collectors.joining;

import java.util.List;
import lotto.controller.dto.LottoResultDto;
import lotto.controller.dto.LottoTicketsDto;
import lotto.controller.dto.RankDto;
import lotto.controller.dto.SalesInfoDto;

public class OutputView {

    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";
    private static final String TICKETS_INFO_DELIMITER = "\n";
    private static final String LOTTO_RESULT_MESSAGE = "\n당첨 통계\n---------";
    private static final String BONUS_RESULT_MESSAGE = "%d개 일치, 보너스 볼 일치(%d원)- %d개\n";
    private static final String NORMAL_RESULT_MESSAGE = "%d개 일치 (%d원)- %d개\n";
    private static final String TOTAL_YIELD_MESSAGE = "총 수익률은 %.2f 입니다.";
    private static final String LOSS_MESSAGE = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
    private static final String PROFIT_MESSAGE = "(기준이 1이기 때문에 결과적으로 이득이라는 의미임)";
    private static final String SALES_INFO_MESSAGE = "\n수동으로 %d장, 자동으로 %d개를 구매했습니다.\n";

    public void printErrorMessage(String errorMessage) {
        System.out.println(ERROR_MESSAGE_PREFIX + errorMessage);
        System.out.println();
    }

    public void printSalesInfo(SalesInfoDto salesInfoDto) {
        System.out.printf(SALES_INFO_MESSAGE, salesInfoDto.getManualCount(), salesInfoDto.getAutoCount());
        printLottoTicketsInfo(salesInfoDto.getLottoTickets());
    }

    public void printLottoTicketsInfo(LottoTicketsDto lottoTicketsDto) {
        String ticketsInfo = lottoTicketsDto.getLottoTickets()
                .stream()
                .map(Object::toString)
                .collect(joining(TICKETS_INFO_DELIMITER));

        System.out.println(ticketsInfo);
    }

    public void printLottoResultMessage() {
        System.out.println(LOTTO_RESULT_MESSAGE);
    }

    public void printYield(LottoResultDto lottoResultDto) {
        printRanksInfo(lottoResultDto);
        if (lottoResultDto.getYield() < 1.00) {
            System.out.printf(TOTAL_YIELD_MESSAGE, lottoResultDto.getYield());
            System.out.println(LOSS_MESSAGE);
            return;
        }

        System.out.printf(TOTAL_YIELD_MESSAGE, lottoResultDto.getYield());
        System.out.println(PROFIT_MESSAGE);
    }

    private void printRanksInfo(LottoResultDto lottoResultDto) {
        List<RankDto> ranks = lottoResultDto.getRanks();
        ranks.forEach(this::printRankInfo);
    }

    private void printRankInfo(RankDto rankDto) {
        if (rankDto.isBonus()) {
            System.out.printf(BONUS_RESULT_MESSAGE, rankDto.getCount(), rankDto.getPrizeMoney(), rankDto.getAmount());
            return;
        }
        System.out.printf(NORMAL_RESULT_MESSAGE, rankDto.getCount(), rankDto.getPrizeMoney(), rankDto.getAmount());
    }
}
