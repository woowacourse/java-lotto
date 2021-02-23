package lotto.view;

import java.util.Map;
import java.util.Map.Entry;
import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;
import lotto.domain.Money;
import lotto.domain.WinningBoard;

public class OutputView {

    public static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    public static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해주세요.";
    public static final String INPUT_WINNING_NUMBERS_MESSAGE = "지난 주 당첨 번호를 입력해주세요.";
    public static final String SHOW_REWARD_STATE_MESSAGE = "당첨 통계";
    public static final String SHOW_TOTAL_YIELD_MESSAGE = "총 수익률은 %.2f입니다.";
    public static final String SPLIT_LINE = "---------";
    public static final String RESULT_FORMAT_MESSAGE = "%s (%d) - %d개";
    public static final String SHOW_TICKETS_STATE_MESSAGE = "수동으로 %d장, 자동으로 %d장을 구매했습니다. 거스름돈은 %d원입니다.";
    public static final String INPUT_MANUAL_TICKETS_NUMBERS_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    public static final String INPUT_MANUAL_TICKET_LOTTO_NUMBERS_INPUT = "수동으로 구매할 번호를 입력해 주세요.";

    public static void printErrorMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }

    public static void printMoneyMessage() {
        System.out.println(INPUT_MONEY_MESSAGE);
    }

    private static void printTicket(LottoTicket lottoTicket) {
        System.out.println(lottoTicket.getLottoNumbers());
    }

    public static void printFixedTicketMessage() {
        System.out.println(INPUT_MANUAL_TICKETS_NUMBERS_MESSAGE);
    }

    public static void printInputFixedTicketMessage() {
        System.out.println(INPUT_MANUAL_TICKET_LOTTO_NUMBERS_INPUT);
    }

    public static void printAllTickets(int fixedTickets, Money money, LottoTickets lottoTickets) {
        System.out.printf(SHOW_TICKETS_STATE_MESSAGE + System.lineSeparator(), fixedTickets,
            money.countTickets() - fixedTickets, money.calculateCharge());
        lottoTickets.getLottoTickets().forEach(OutputView::printTicket);
    }

    public static void printWinningNumbers() {
        System.out.println(INPUT_WINNING_NUMBERS_MESSAGE);
    }

    public static void printBonusNumber() {
        System.out.println(INPUT_BONUS_BALL_MESSAGE);
    }

    public static void printWinningResultTitle() {
        System.out.println(SHOW_REWARD_STATE_MESSAGE);
        printSplitLine();
    }

    private static void printSplitLine() {
        System.out.println(SPLIT_LINE);
    }

    public static void printProfit(float profit, Map<WinningBoard, Integer> result) {
        System.out.printf(SHOW_TOTAL_YIELD_MESSAGE + System.lineSeparator(), profit);
        result.entrySet().forEach(OutputView::printLottoResult);
    }

    private static void printLottoResult(Entry<WinningBoard, Integer> result) {
        if (!result.getKey().equals(WinningBoard.ZERO)) {
            System.out.printf(RESULT_FORMAT_MESSAGE + System.lineSeparator(),
                result.getKey().getMessage(), result.getKey().getReward(), result.getValue());
        }
    }
}
