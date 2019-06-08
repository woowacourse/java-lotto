package lotto.view;

import lotto.domain.Exceptions.LottoTicketException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);


    public static String lottoMoney() {
        System.out.println(ConsoleMessages.BUY_MONEY.message());
        return scanner.nextLine();
    }

    public static String manualLottoCount() {
        System.out.println(ConsoleMessages.MANUAL_COUNT.message());
        return scanner.nextLine();
    }


    public static List<String> manualLottoNumber(String manualCount) {
        int iterate;
        try {
            iterate = Integer.parseInt(manualCount);
        } catch (NumberFormatException e) {
            throw new LottoTicketException();
        }
        if (iterate > 0) {
            return manualLottoNumberStrings(iterate);
        }
        return new ArrayList<>();
    }

    private static List<String> manualLottoNumberStrings(int iterate) {
        System.out.println(ConsoleMessages.MANUAL_NUMBER.message());
        List<String> manualNumbers = new ArrayList<>();
        for (int i = 0; i < iterate; i++) {
            manualNumbers.add(scanner.nextLine());
        }
        return manualNumbers;
    }

    public static String winningLottoNumber() {
        System.out.println(ConsoleMessages.WINNING_NUMBER.message());
        return scanner.nextLine();
    }

    public static String winningLottoBonus() {
        System.out.println(ConsoleMessages.WINNING_BONUS.message());
        return scanner.nextLine();
    }
}
