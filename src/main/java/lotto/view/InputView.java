package lotto.view;

import lotto.domain.UserLottoDto;
import lotto.domain.WinningLottoDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static UserLottoDto inputUserLotto() {
        String lottoMoney;
        List<String> manualNumber = new ArrayList<>();
        System.out.println(ConsoleMessages.BUY_MONEY.message());
        lottoMoney = scanner.nextLine();
        System.out.println(ConsoleMessages.MANUAL_COUNT.message());
        int iter = Integer.parseInt(scanner.nextLine());
        System.out.println(ConsoleMessages.MANUAL_NUMBER.message());
        for (int i = 0; i < iter; i++) {
            manualNumber.add(scanner.nextLine());
        }
        return new UserLottoDto(lottoMoney, manualNumber);
    }

    public static WinningLottoDto inputWinningLotto() {
        String numbers, bonus;
        System.out.println(ConsoleMessages.WINNING_NUMBER.message());
        numbers = scanner.nextLine();
        System.out.println(ConsoleMessages.WINNING_BONUS.message());
        bonus = scanner.nextLine();

        return new WinningLottoDto(numbers, bonus);
    }
}
