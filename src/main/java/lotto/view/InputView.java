package lotto.view;

import lotto.domain.exceptions.LottoTicketException;
import lotto.presentation.UserLottoPresentation;
import lotto.presentation.WinningLottoPresentation;

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

    private static List<String> manualLottoNumber(int iterate) {
        System.out.println(ConsoleMessages.MANUAL_NUMBER.message());
        List<String> manualNumbers = new ArrayList<>();
        for (int i = 0; i < iterate; i++) {
            manualNumbers.add(scanner.nextLine());
        }
        return manualNumbers;
    }

    public static List<String> manualLottoNumber(String iterate) {
        int iterateNumber;
        try {
            iterateNumber = Integer.parseInt(iterate);
        } catch (NumberFormatException e) {
            throw new LottoTicketException();
        }
        return manualLottoNumber(iterateNumber);
    }

    public static String winningLottoNumber() {
        System.out.println(ConsoleMessages.WINNING_NUMBER.message());
        return scanner.nextLine();
    }

    public static String winningLottoBonus() {
        System.out.println(ConsoleMessages.WINNING_BONUS.message());
        return scanner.nextLine();
    }

    public static UserLottoPresentation userLottoPresentation() {
        String money = lottoMoney();
        String manualCount = manualLottoCount();
        return new UserLottoPresentation(money, manualCount, manualLottoNumber(manualCount));
    }

    public static WinningLottoPresentation winningLottoPresentation() {
        return new WinningLottoPresentation(winningLottoNumber(), winningLottoBonus());
    }
}
