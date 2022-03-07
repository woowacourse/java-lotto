package lotto.view;

import java.util.Scanner;

public class InputView {
    private static final String REQUEST_BONUS_NUMBER_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String REQUEST_WIN_NUMBERS = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String REQUEST_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String REQUEST_NUMBER_MESSAGE = "숫자를 입력해 주세요";
    private static final String REQUEST_MANUAL_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String REQUEST_INTEGER_INPUT_MESSAGE = "0이상의 수를 입력해주세요.";
    private static final String REQUEST_MANUAL_NUMBERS = "수동으로 구매할 번호를 입력해 주세요.";
    private static final Scanner scanner = new Scanner(System.in);

    public static int requestAmount() {
        try {
            System.out.println(REQUEST_AMOUNT_MESSAGE);
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(REQUEST_NUMBER_MESSAGE);
            return requestAmount();
        }
    }

    public static String requestWinNumbers() {
        System.out.println(REQUEST_WIN_NUMBERS);
        return scanner.nextLine();
    }

    public static int requestBonusNumber() {
        try {
            System.out.println(REQUEST_BONUS_NUMBER_MESSAGE);
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(REQUEST_NUMBER_MESSAGE);
            return requestAmount();
        }
    }

    public static int requestManualTicketCount() {
        try {
            System.out.println(REQUEST_MANUAL_LOTTO_COUNT);
            int manualTicketCount = Integer.parseInt(scanner.nextLine());
            validateNotNegative(manualTicketCount);
            return manualTicketCount;
        } catch (NumberFormatException e) {
            OutputView.printErrorMessage(REQUEST_NUMBER_MESSAGE);
            return requestManualTicketCount();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return requestManualTicketCount();
        }
    }

    private static void validateNotNegative(int number) {
        if (number < 0) {
            throw new IllegalArgumentException(REQUEST_INTEGER_INPUT_MESSAGE);
        }
    }

    public static void requestManualTicket() {
        System.out.println(REQUEST_MANUAL_NUMBERS);
    }

    public static String requestNumbers() {
        return scanner.nextLine();
    }
}
