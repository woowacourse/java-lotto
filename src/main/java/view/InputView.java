package view;

import domain.LottoFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputView {
    static Scanner sc = new Scanner(System.in);

    public static int inputPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        int purchaseAmount = validatePurchaseAmount(sc.nextLine());
        System.out.println(purchaseAmount / LottoFactory.LOTTO_PRICE + "개를 구매했습니다.");
        return purchaseAmount;
    }

    public static List<Integer> inputWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        try {
            List<Integer> winningNumbers = Arrays.stream(sc.nextLine()
                            .split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .toList();
            validateWinningNumbers(winningNumbers);
            validateWinningNumbersUnique(winningNumbers);
            return winningNumbers;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("당첨 번호는 숫자만 가능합니다.");
        }
    }

    public static int inputBonusBall(List<Integer> winningNumbers) {
        System.out.println("보너스 볼을 입력해 주세요.");
        try {
            int bonusBall = Integer.parseInt(sc.nextLine());
            validateNumber(bonusBall);
            validateBonusBallUnique(winningNumbers, bonusBall);
            return bonusBall;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("보너스 볼을 형식에 맞게 입력해주세요.");
        }
    }

    private static int validatePurchaseAmount(String purchaseAmount) {
        try {
            int amount = Integer.parseInt(purchaseAmount);
            if (amount < LottoFactory.LOTTO_PRICE) {
                throw new IllegalArgumentException(String.format("구입 금액은 %d원 이상부터 가능합니다.", LottoFactory.LOTTO_PRICE));
            }
            if (amount % LottoFactory.LOTTO_PRICE != 0) {
                throw new IllegalArgumentException(String.format("구입 금액은 %d원 단위로 가능합니다.", LottoFactory.LOTTO_PRICE));
            }
            return amount;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("구입 금액은 숫자만 입력 가능합니다.");
        }
    }

    private static void validateWinningNumbers(List<Integer> winningNumbers) {
        if(winningNumbers.size() != 6) {
            throw new IllegalArgumentException("당첨 번호는 6개여야 합니다.");
        }
        for (int num : winningNumbers) {
            validateNumber(num);
        }
    }

    private static void validateNumber(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("번호는 1 ~ 45만 입력 가능합니다.");
        }
    }

    private static void validateWinningNumbersUnique(List<Integer> winningNumbers) {
        if (winningNumbers.size() != winningNumbers.stream().distinct().count()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    private static void validateBonusBallUnique(List<Integer> winningNumbers, int bonusBall) {
        if (winningNumbers.contains(bonusBall)) {
            throw new IllegalArgumentException("보너스 볼은 로또 번호와 중복될 수 없습니다.");
        }
    }
}
