package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static Scanner scanner = new Scanner(System.in);

    public static String enterMoney() {
        System.out.println("구입 금액을 입력해주세요.");
        return scanner.nextLine();
    }

    public static String enterManualTicketSize() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static List<String> enterManualTickets(int ticketSize) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<String> manualTicketsInput = new ArrayList<>();
        for (int i = 0; i < ticketSize; i++) {
            manualTicketsInput.add(scanner.nextLine());
        }
        return manualTicketsInput;
    }

    public static String enterLastWeekWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public static String enterBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        return scanner.nextLine();
    }
}