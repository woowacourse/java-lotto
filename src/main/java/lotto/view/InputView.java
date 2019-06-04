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
        System.out.println("금액을 입력");
        lottoMoney = scanner.nextLine();
        System.out.println("수동몇장?");
        int iter = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < iter; i++) {
            manualNumber.add(scanner.nextLine());
        }
        return new UserLottoDto(lottoMoney, manualNumber);
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
