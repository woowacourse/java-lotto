package lotto.view;

import lotto.domain.WinningLottoDto;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static String inputLottoMoney() {
        System.out.println("금액을 입력");
        return scanner.nextLine();
    }

    public static WinningLottoDto inputWinningLotto() {
        String numbers, bonus;
        System.out.println("당첨번호");
        numbers = scanner.nextLine();
        System.out.println("보너스");
        bonus = scanner.nextLine();

        return new WinningLottoDto(numbers, bonus);
    }
}
