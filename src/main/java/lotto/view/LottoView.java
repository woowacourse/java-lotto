package lotto.view;

import lotto.domain.Lottos;
import lotto.domain.Rank;

import java.util.ArrayList;
import java.util.Scanner;

public class LottoView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해주세요";
    private static final String LOTTO_COUNT_MESSAGE = "\n수동으로 %d장, 자동으로 %d개를 구매했습니다.\n";
    private static final String LAST_WEEK_LOTTO_MESSAGE = "\n지난 주 당첨 번호를 입력해 주세요.";
    private static final String BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String RESULT_TITLE_MESSAGE = "\n당첨 통계\n----------";
    private static final String CHANGE_LINE = "\n";
    private static final String LOTTO_RESULT_MESSSAGE = "%d개 일치 (%d원)- %d개\n";
    private static final String SECOND_RANK_RESULT_MESSAGE = "%d개 일치, 보너스 볼 일치 (%d)원- %d개\n";
    private static final String EARNING_RATE_MESSAGE = "총 수익률은 %s입니다.";
    private static final String INPUT_MANUAL_COUNT_MESSAGE = "\n수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String INPUT_MANUAL_NUMBER_MESSAGE = "\n수동으로 구매할 번호를 입력해 주세요.";

    public static String requestMoney() {
        System.out.println(INPUT_MONEY_MESSAGE);
        return SCANNER.nextLine();
    }

    public static String requestManualLottoCount() {
        System.out.println(INPUT_MANUAL_COUNT_MESSAGE);
        return SCANNER.nextLine();
    }

    public static void displayManualNumberMessage() {
        System.out.println(INPUT_MANUAL_NUMBER_MESSAGE);
    }

    public static String requestManualNumber() {
        return SCANNER.nextLine();
    }

    public static void displayLottoCount(int manualCount, int randomCount) {
        System.out.printf(LOTTO_COUNT_MESSAGE, manualCount, randomCount);
    }

    public static void displayLottoGroup(Lottos lottos) {
        for (ArrayList<Integer> lotto : lottos.changeFormat()) {
            System.out.print(lotto + CHANGE_LINE);
        }
    }

    public static String requestWinningNumber() {
        System.out.println(LAST_WEEK_LOTTO_MESSAGE);
        return SCANNER.nextLine();
    }

    public static String requestBonusBallNumber() {
        System.out.println(BONUS_BALL_MESSAGE);
        return SCANNER.nextLine();
    }

    public static void displayResultMessage() {
        System.out.println(RESULT_TITLE_MESSAGE);
    }

    public static void displayResult(Rank rank, int count) {
        if (rank == Rank.LOSE) {
            return;
        }
        System.out.printf(findMessage(rank), rank.getMatchCount(), rank.getPrize(), count);
    }

    private static String findMessage(Rank rank) {
        if (rank == Rank.SECOND) {
            return SECOND_RANK_RESULT_MESSAGE;
        }
        return LOTTO_RESULT_MESSSAGE;
    }

    public static void displayEarningRate(String earningRate) {
        System.out.printf(EARNING_RATE_MESSAGE, earningRate);
    }
}
