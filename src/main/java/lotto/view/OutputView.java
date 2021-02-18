package lotto.view;

import lotto.domain.LottoTicket;
import lotto.domain.LottoTickets;

public class OutputView {

    public static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    public static final String SHOW_TICKETS_COUNT_MESSAGE = "개를 구매했습니다.";
    public static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해주세요.";
    public static final String INPUT_WINNING_NUMBERS_MESSAGE = "지난 주 당첨 번호를 입력해주세요.";
    public static final String SHOW_REWARD_STATE_MESSAGE = "당첨 통계";
    public static final String SHOW_TOTAL_YIELD_MESSAGE = "총 수익률은 %.2f입니다.";
    public static final String SPLIT_LINE = "---------";

    public static void printErrorMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }

    public static void printMoneyMessage() {
        System.out.println(INPUT_MONEY_MESSAGE);
    }

    public static void printTicketCountMessage(int counts) {
        System.out.println(counts + SHOW_TICKETS_COUNT_MESSAGE);
    }

    private static void printTicket(LottoTicket lottoTicket) {
        System.out.println(lottoTicket.getLottoNumbers());
    }

    public static void printAllTickets(LottoTickets lottoTickets) {
        lottoTickets.toList().forEach(OutputView::printTicket);
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

    public static void printProfit(float profit) {
        System.out.printf(SHOW_TOTAL_YIELD_MESSAGE + System.lineSeparator(), profit);
    }

}
