package lottogame.view;

import lottogame.utils.InvalidBonusBallNumberException;
import lottogame.utils.InvalidMoneyException;
import lottogame.utils.InvalidWinningLottoException;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputView {
    private static Scanner scanner;
    private static final Pattern LOTTO_NUMBER_INPUT_PATTERN =
            Pattern.compile("^(\\d{1,2},\\s){5}\\d{1,2}$");

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String input() {
        return scanner.nextLine();
    }

    public int inputMoney() {
        try {
            System.out.println("구입 금액을 입력해 주세요.");
            return Integer.parseInt(input());
        } catch (NumberFormatException e) {
            throw new InvalidMoneyException();
        }
    }

    public List<Integer> inputWinningLottoNumbers() {
        System.out.println("\n지난 주 당첨 번호를 입력해 주세요.");
        String numbers = input();
        if (!LOTTO_NUMBER_INPUT_PATTERN.matcher(numbers).matches()) {
            throw new InvalidWinningLottoException();
        }
        return Arrays.stream(numbers.split(", "))
                .mapToInt(number -> Integer.parseInt(number))
                .boxed()
                .collect(Collectors.toList());
    }

    public int inputBonusNumber() {
        try {
            System.out.println("보너스 볼을 입력해 주세요.");
            return Integer.parseInt(input());
        } catch (NumberFormatException e) {
            throw new InvalidBonusBallNumberException();
        }
    }
}
