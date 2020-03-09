package lotto.view;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTicketCount;
import lotto.domain.WinningValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class OutputView {
    private static final String WINNING_ANALYSIS_MESSAGE = "당첨 통계\n" + "---------";
    private static final String LOTTO_TICKET_BOUGHT_INFORMATION_MESSAGE = "수동으로 %d장, 자동으로 %d장을 구매했습니다.";
    private static final String HIT_COUNT_MESSAGE = "(%d원) - %d개";
    private static final String REWARD_RATE_MESSAGE = "총 수익률은 %d";
    private static final String PERCENT_MESSAGE = "% 입니다.";

    public static void printPurchaseCount(LottoTicketCount lottoTicketCount) {
        System.out.println(String
                .format(LOTTO_TICKET_BOUGHT_INFORMATION_MESSAGE, lottoTicketCount.getManualCount(), lottoTicketCount.getAutoCount()));
    }

    public static void printLottoNumbers(Set<LottoTicket> lottoTickets) {
        for (LottoTicket lottoTicket : lottoTickets) {
            ArrayList<LottoNumber> lotto = new ArrayList<>(lottoTicket.getLottoTicket());
            System.out.println(sortLotto(lotto));
        }
        System.out.println();
    }

    private static ArrayList<Integer> sortLotto(ArrayList<LottoNumber> lotto) {
        ArrayList<Integer> sortedLotto = new ArrayList<>();
        for (LottoNumber lottoNumber : lotto) {
            sortedLotto.add(lottoNumber.getLottoNumber());
        }
        Collections.sort(sortedLotto);
        return sortedLotto;
    }

    public static void printLottoResults(Map<WinningValue, Integer> lottoResult) {
        System.out.println(WINNING_ANALYSIS_MESSAGE);
        lottoResult.entrySet().forEach(OutputView::printLottoResult);
        System.out.println();
    }

    private static void printLottoResult(Map.Entry<WinningValue, Integer> result) {
        System.out.print(result.getKey().getMessage());
        System.out.println(String.format(HIT_COUNT_MESSAGE, result.getKey().getReward(), result.getValue()));
    }

    public static void printRewardRate(int rewardRate) {
        System.out.println(String.format(REWARD_RATE_MESSAGE, rewardRate) + PERCENT_MESSAGE);
    }
}