package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static int requestAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        return getIntegerInput();
    }

    private static int getIntegerInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("숫자를 입력해 주세요");
            return getIntegerInput();
        }
    }

    public static String requestWinNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static int requestBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return getIntegerInput();
    }

    public static int requestManualTicketCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return getIntegerInput();
    }

    public static List<String> requestManualNumbers(int manualTicketsCount) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<String> inputs = new ArrayList<>();
        for (int count = 0; count < manualTicketsCount; count++) {
            inputs.add(scanner.nextLine());
        }
        return inputs;
    }
}
