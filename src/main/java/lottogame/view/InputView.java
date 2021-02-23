package lottogame.view;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Pattern PATTERN = Pattern.compile("([0-9])+");

    public static int inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        String money = SCANNER.nextLine();
        validateInteger(money);
        return Integer.parseInt(money);
    }

    public static Set<Integer> inputWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        return getWinningNumbers(PATTERN.matcher(SCANNER.nextLine()));
    }

    private static Set<Integer> getWinningNumbers(Matcher matcher) {
        Set<Integer> winningNumbers = new HashSet<>();
        while (matcher.find()) {
            String number = matcher.group();
            validateInteger(number);
            winningNumbers.add(Integer.parseInt(number));
        }
        return winningNumbers;
    }

    public static int inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        String number = SCANNER.nextLine();
        validateInteger(number);
        return Integer.parseInt(number);
    }

    private static void validateInteger(String string) {
        if (!string.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("정수만 입력 가능합니다.");
        }
    }
}
