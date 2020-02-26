package lotto.view;

import java.util.stream.Collectors;
import lotto.model.LottoNumber;
import lotto.model.Ticket;

import java.util.List;

public class OutputView {

    private static final String MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String MANUAL_NUMBER_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String LOTTO_COUNT_MESSAGE = "%d개를 구입했습니다.";
    private static final String INPUT_WIN_NUMBER_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String RESULT_MESSAGE = "\n당첨 통계\n---------";
    private static final String RESULT_DETAIL_MESSAGE = "%d개 일치 (%d원)- %d개";
    private static final String RESULT_BONUS_MESSAGE = "%d개 일치, 보너스 볼 일치 (%d원) - %d개";
    private static final String YIELD_MESSAGE = "총 수익률은 %d%%입니다.";
    private static final int SECOND_PRIZE = 30_000_000;

    public static void printInputMoney() {
        System.out.println(MONEY_MESSAGE);
    }

    public static void printInputManualNumber() {
        System.out.println(MANUAL_NUMBER_MESSAGE);
    }

    public static void printHowManyTicketsPurchase(int count) {
        System.out.printf(LOTTO_COUNT_MESSAGE, count);
        System.out.println();
    }

    public static void printAutoNumbers(List<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            System.out.println(ticket.getTicket().stream().mapToInt(LottoNumber::getNumber).boxed()
                .collect(Collectors.toList()));
        }
    }

    public static void printInputWinNumber() {
        System.out.println(INPUT_WIN_NUMBER_MESSAGE);
    }

    public static void printInputBonusNumber() {
        System.out.println(INPUT_BONUS_NUMBER_MESSAGE);
    }

    public static void printResult() {
        System.out.println(RESULT_MESSAGE);
    }

    public static void printCorrectResult(int correct, int prize, int count) {
        if (prize == SECOND_PRIZE) {
            System.out.printf(RESULT_BONUS_MESSAGE, correct, prize, count);
            System.out.println();
            return;
        }
        System.out.printf(RESULT_DETAIL_MESSAGE, correct, prize, count);
        System.out.println();
    }

    public static void printYield(int yield) {
        System.out.printf(YIELD_MESSAGE, yield);
        System.out.println();
    }
}
