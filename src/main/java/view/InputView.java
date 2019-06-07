package view;

import domain.LottoNumber;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static int inputPurchaseAmount() {
        try {
            System.out.println("구입금액을 입력해주세요.");
            String purchasedAmount = SCANNER.nextLine().trim();
            return Integer.parseInt(purchasedAmount);
        } catch (NumberFormatException e) {
            System.out.println("정수만 입력할 수 있습니다.");
            return inputPurchaseAmount();
        }
    }

    public static List<Integer> inputWinningNumbers() {
        try {
            System.out.println("지난 주 당첨 번호를 입력해주세요.");
            return Arrays.stream(SCANNER.nextLine().split(","))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            System.out.println("정수만 입력할 수 있습니다.");
            return inputWinningNumbers();
        }
    }

    public static int inputBonusNumber() {
        try {
            System.out.println("보너스 볼을 입력해주세요.");
            String bonusNumber = SCANNER.nextLine().trim();
            return Integer.parseInt(bonusNumber);
        } catch (NumberFormatException e) {
            System.out.println("정수만 입력할 수 있습니다.");
            return inputBonusNumber();
        }
    }
}
