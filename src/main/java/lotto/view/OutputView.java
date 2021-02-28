package lotto.view;

import lotto.domain.LottoResultStatistics;
import lotto.domain.lottos.LottoNumber;
import lotto.domain.lottos.LottoTicket;
import lotto.domain.lottos.LottoTickets;
import lotto.domain.lottos.amount.LottoAmount;
import lotto.domain.lottos.rank.LottoRank;
import lotto.domain.money.Money;

import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String HOW_MUCH_BOUGHT = "수동으로 %d장, 자동으로 %d장을 구매했습니다.";
    private static final String REWARD_RESULT_BOARD = "당첨 통계";
    private static final String NEW_LINE = System.lineSeparator();
    private static final String RESULT_LINE = "---------";
    private static final String EACH_RESULT = "%d개 일치 (%d원)- %d개";
    private static final String EACH_RESULT_WITH_BONUS = "%d개 일치, 보너스 볼 일치(%d원)- %d개";
    private static final String FINAL_RESULT = "총 수익률은 %d%%입니다.";

    public static void printTickets(LottoTickets lottoTickets, LottoAmount lottoAmount) {
        howMuchBought(lottoAmount.getAutoAmount(), lottoAmount.getManualAmount());
        lottoTickets.getLottoTickets()
                .forEach(OutputView::printTicket);
        System.out.print(NEW_LINE);
    }

    private static void howMuchBought(int autoAmount, int manualAmount) {
        System.out.print(NEW_LINE);
        System.out.printf(HOW_MUCH_BOUGHT, manualAmount, autoAmount);
        System.out.print(NEW_LINE);
    }

    public static void printTicket(LottoTicket lottoTicket) {
        System.out.println(
                lottoTicket.getLottoNumbers()
                        .stream()
                        .map(LottoNumber::getNumber)
                        .map(String::valueOf)
                        .collect(Collectors.joining(", ", "[", "]")));
    }

    public static void printRewardResultBoard(LottoResultStatistics resultStatistics) {
        printRewardResultBoardTitle();
        Map<LottoRank, Integer> lottoResult = resultStatistics.getLottoResult();
        lottoResult.entrySet()
                .stream()
                .filter(entry -> !entry.getKey().equals(LottoRank.SIXTH_PLACE))
                .forEach(OutputView::printEachStatistics);
    }

    private static void printRewardResultBoardTitle() {
        System.out.print(NEW_LINE);
        System.out.println(REWARD_RESULT_BOARD);
        System.out.println(RESULT_LINE);
    }

    private static void printEachStatistics(Map.Entry<LottoRank, Integer> entry) {
        System.out.printf(getResultFormatMessage(entry.getKey()) + NEW_LINE, entry.getKey().getMatches(),
                entry.getKey().getReward(), entry.getValue());
    }

    private static String getResultFormatMessage(LottoRank rank) {
        if (rank.equals(LottoRank.SECOND_PLACE)) {
            return EACH_RESULT_WITH_BONUS;
        }
        return EACH_RESULT;
    }

    public static void printFinalResult(LottoResultStatistics lottoResultStatistics, Money money) {
        System.out.printf(FINAL_RESULT, lottoResultStatistics.calculateEarning(money));
    }

    public static void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }
}
