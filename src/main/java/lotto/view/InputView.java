package lotto.view;

import lotto.domain.LottoNumber;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    private InputView() {
    }

    public static int inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");

        String input = scanner.nextLine();
        if (!input.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("숫자를 입력해주세요");
        }

        return Integer.parseInt(input);
    }

    public static List<LottoNumber> winningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String[] inputs = scanner.nextLine().split(", ");

        if (!Arrays.stream(inputs)
                .allMatch(s -> s.chars().allMatch(Character::isDigit))) {
            throw new IllegalArgumentException("당첨번호는 숫자여야 합니다.");
        }

        return Arrays.stream(inputs)
                .map(s -> new LottoNumber(Integer.parseInt(s)))
                .collect(Collectors.toList());
    }

    public static int bonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요");
        String bonus = scanner.nextLine();

        if (!bonus.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("보너스 번호는 숫자여야 합니다.");
        }

        return Integer.parseInt(bonus);
    }

    public static int inputManualPurchaseCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        String input = scanner.nextLine();

        if (!input.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("숫자를 입력해주세요.");
        }

        return Integer.parseInt(input);
    }

    public static String inputManualPurchase() {
        return scanner.nextLine();
    }

    public static void inputManualPurChaseMessage() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }
}
