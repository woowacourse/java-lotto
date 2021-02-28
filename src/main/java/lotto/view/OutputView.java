package lotto.view;

import lotto.domain.*;

import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String ASK_HOW_MUCH_TO_BUY = "구입금액을 입력해 주세요.";
    private static final String ASK_WINNER_LOTTO_TICKET = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String ASK_WINNER_BONUS_NUMBER = "보너스 볼을 입력해 주세요.";
    private static final String REWARD_RESULT_BOARD = "당첨 통계";
    private static final String NEW_LINE = System.lineSeparator();
    private static final String RESULT_LINE = "---------";
    private static final String EACH_RESULT = "%d개 일치 (%d원)- %d개";
    private static final String EACH_RESULT_WITH_BONUS = "%d개 일치, 보너스 볼 일치(%d원)- %d개";
    private static final String FINAL_RESULT = "총 수익률은 %d%%입니다.";
    private static final String ASK_HOW_MANY_MANUAL_LOTTO_TICKETS = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String ASK_MANUAL_LOTTO_TICKET = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String HOW_MANY_TICKETS_BOUGHT = "수동으로 %d장, 자동으로 %d개를 구매했습니다.";

    public static void askHowMuchToBuy() {
        System.out.println(ASK_HOW_MUCH_TO_BUY);
    }

    public static void printTickets(LottoTickets lottoTickets) {
        lottoTickets.getTickets()
                .stream()
                .forEach(lottoTicket -> printTicket(lottoTicket));
        System.out.print(NEW_LINE);
    }

    public static void printTicket(LottoTicket lottoTicket) {
        System.out.println(
                lottoTicket.getNumbers()
                        .stream()
                        .map(LottoNumber::getNumber)
                        .map(String::valueOf)
                        .collect(Collectors.joining(", ", "[", "]")));
    }

    public static void askWinnerTicket() {
        System.out.println(ASK_WINNER_LOTTO_TICKET);
    }

    public static void askWinnerBonusNumber() {
        System.out.println(ASK_WINNER_BONUS_NUMBER);
    }

    public static void printRewardResultBoard() {
        System.out.print(NEW_LINE);
        System.out.println(REWARD_RESULT_BOARD);
        System.out.println(RESULT_LINE);
    }

    public static void printStatistics(LottoResultStatistics resultStatistics) {
        Map<LottoRank, Integer> lottoResult = resultStatistics.getResult();
        lottoResult.entrySet()
                .stream()
                .filter(entry -> !entry.getKey().equals(LottoRank.SIXTH_PLACE))
                .forEach(entry -> printEachStatistics(entry));
    }

    private static void printEachStatistics(Map.Entry<LottoRank, Integer> entry) {
        if (entry.getKey().equals(LottoRank.SECOND_PLACE)) {
            System.out.printf((EACH_RESULT_WITH_BONUS) + NEW_LINE, entry.getKey().getMatches(),
                    entry.getKey().getReward(), entry.getValue());
            return;
        }
        System.out.printf((EACH_RESULT) + NEW_LINE, entry.getKey().getMatches(),
                entry.getKey().getReward(), entry.getValue());
    }

    public static void printFinalResult(LottoResultStatistics lottoResultStatistics, Money money) {
        System.out.printf(FINAL_RESULT, lottoResultStatistics.earning(money));
    }

    public static void printErrorMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public static void askHowManyManualTicketsToBuy() {
        System.out.print(NEW_LINE);
        System.out.println(ASK_HOW_MANY_MANUAL_LOTTO_TICKETS);
    }

    public static void askManualTicketNumbers() {
        System.out.print(NEW_LINE);
        System.out.println(ASK_MANUAL_LOTTO_TICKET);
    }

    public static void printHowManyTicketsBought(int manualCount, int autoCount) {
        System.out.print(NEW_LINE);
        System.out.printf(HOW_MANY_TICKETS_BOUGHT, manualCount, autoCount);
        System.out.print(NEW_LINE);
    }
}
