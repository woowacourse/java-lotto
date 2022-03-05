package lotto.view;

import static java.lang.System.out;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import lotto.domain.vo.ManualTicketCount;

public enum InputView {

    INSTANCE;

    private static final Scanner READER = new Scanner(System.in);
    public static final String INPUT_MONEY_MESSAGE = "구입금액을 입력해 주세요.";
    public static final String INPUT_WINNING_NUMBERS_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    public static final String INPUT_BONUS_BALL_MESSAGE = "보너스 볼을 입력해 주세요.";
    public static final String INPUT_TICKET_SIZE_MANUALLY_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    public static final String INPUT_LOTTO_NUMBERS_MANUALLY_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";

    private void printExceptionMessage(RuntimeException exception) {
        out.println(exception.getMessage());
    }

    public String inputMoney() {
        out.println(INPUT_MONEY_MESSAGE);
        return READER.nextLine();
    }

    public String inputTicketSizeManually() {
        out.println(INPUT_TICKET_SIZE_MANUALLY_MESSAGE);
        return READER.nextLine();
    }

    public List<String> inputTicketNumbersManually(ManualTicketCount manualTicketCount) {
        out.println(INPUT_LOTTO_NUMBERS_MANUALLY_MESSAGE);
        List<String> manualTicketNumbers = new LinkedList<>();
        for (int i = 0; i < manualTicketCount.ticketNumber(); i++) {
            manualTicketNumbers.add(READER.nextLine());
        }
        return manualTicketNumbers;
    }

    public String inputWinningNumbers() {
        out.println(INPUT_WINNING_NUMBERS_MESSAGE);
        return READER.nextLine();
    }

    public String inputBonusBall() {
        out.println(INPUT_BONUS_BALL_MESSAGE);
        return READER.nextLine();
    }

    public void closeResource() {
        READER.close();
    }

    public void printErrorMessage(Exception e) {
        out.println(e.getMessage());
    }
}
