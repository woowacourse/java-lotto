package view;

import java.util.List;

public class Validator {
    public static void validateWinningNumbers(List<Integer> winningNumbers) {
        if(winningNumbers.size() != 6) {
            throw new IllegalArgumentException("당첨 번호는 6개여야 합니다.");
        }
        for (int num : winningNumbers) {
            validateNumber(num);
        }
    }

    public static void validateNumber(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("번호는 1 ~ 45만 입력 가능합니다.");
        }
    }

    public static void validateWinningNumbersUnique(List<Integer> winningNumbers) {
        if (winningNumbers.size() != winningNumbers.stream().distinct().count()) {
            throw new IllegalArgumentException("로또 번호는 중복될 수 없습니다.");
        }
    }

    public static void validateBonusBallUnique(List<Integer> winningNumbers, int bonusBall) {
        if (winningNumbers.contains(bonusBall)) {
            throw new IllegalArgumentException("보너스 볼은 로또 번호와 중복될 수 없습니다.");
        }
    }
}
