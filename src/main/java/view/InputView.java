package view;

import domain.Lotto;
import domain.Money;
import domain.WinningLotto;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static Money inputPurchaseMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        int money = SCANNER.nextInt();
        return new Money(money);
    }

    public static WinningLotto inputWinningLotto() {
        return new WinningLotto(inputLotto(), inputBonusBall());
    }

    private static Lotto inputLotto() {
        System.out.println("지난 주 당첨 로또를 입력해주세요.");
        String input = SCANNER.nextLine();
        List<Integer> lottoNumbers = Arrays.stream(input.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        return new Lotto(lottoNumbers);
    }

    private static int inputBonusBall() {
        System.out.println("보너스 볼을 입력해주세요.");
        return SCANNER.nextInt();
    }
}
