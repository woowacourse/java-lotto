package lotto.view;

import lotto.domain.LottoTicket;
import lotto.domain.WinningValue;

import java.util.Map;
import java.util.Set;

public class OutputView {
    private static final String WINNING_ANALYSIS_MESSAGE = "당첨 통계\n" + "---------";
    private static final String LOTTO_TICKET_BOUGHT_INFORMATION_MESSAGE = "%d 개를 구매했습니다.";
    private static final String HIT_COUNT_MESSAGE = "(%d원) - %d개)";
    private static final String REWARD_RATE_MESSAGE = "총 수익률은 %d 입니다.";

    public static void printPurchaseCount(int lottoTicketCount) {
        System.out.println(String.format(LOTTO_TICKET_BOUGHT_INFORMATION_MESSAGE, lottoTicketCount));
    }

    public static void printLottoNumbers(Set<LottoTicket> lottoTickets) {
        for (LottoTicket lottoTicket : lottoTickets) {
            System.out.println(lottoTicket.getLottoTicket());
        }
    }

    public static void printLottoResults(Map<WinningValue, Integer> lottoResult) {
        System.out.println(WINNING_ANALYSIS_MESSAGE);
        lottoResult.entrySet().forEach(OutputView::printLottoResult);

    }

    private static void printLottoResult(Map.Entry<WinningValue, Integer> result) {
        System.out.print(result.getKey().getMessage());
        System.out.print(String.format(HIT_COUNT_MESSAGE, result.getKey().getReward(), result.getValue()));
    }

    public static void printRewardRate(int rewardRate) {
        System.out.println(String.format(REWARD_RATE_MESSAGE,rewardRate));
    }
}