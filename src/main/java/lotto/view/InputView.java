package lotto.view;

import java.util.*;
import java.util.stream.Collectors;

public class InputView {
    private static final int MINIMUM_CANDIDATE_NUMBER = 1;
    private static final int MAXIMUM_CANDIDATE_NUMBER = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    private static Scanner scanner = new Scanner(System.in);

    private InputView(){
    }

    public static String inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");

        return scanner.nextLine();
    }

    public static List<Integer> winningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String[] numbers = scanner.nextLine().split(", ");
        validateCheck(numbers);

        return Arrays.stream(numbers)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static int bonusNumber(List<Integer> numbers) {
        System.out.println("보너스 볼을 입력해 주세요");
        String bonusNum = scanner.nextLine();
        validateBounus(numbers, bonusNum);

        return Integer.parseInt(bonusNum);
    }

    private static void validateCheck(String[] numbers) {
        for (String number : numbers) {
            validateNumber(number);
        }
        validateNumbers(numbers);
    }

    private static void validateNumber(String number) {
        if (!number.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("당첨 번호는 숫자여야 합니다.");
        }
        if (Integer.parseInt(number) < MINIMUM_CANDIDATE_NUMBER &&
                MAXIMUM_CANDIDATE_NUMBER < Integer.parseInt(number)) {
            throw new IllegalArgumentException("당첨 번호는 1 에서 45 사이 여야 합니다.");
        }
    }

    private static void validateNumbers(String[] numbers) {
        Set<String> numberGroup = new HashSet<>(Arrays.asList(numbers));
        if (numbers.length != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("당첨 번호는 총 6개 이어야 합니다.");
        }
        if (numberGroup.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("중복되는 번호는 안됩니다.");
        }
    }

    private static void validateBounus(List<Integer> numbers, String bonusNum) {
        if (!bonusNum.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("보너스 번호는 숫자여야 합니다.");
        }
        if (Integer.parseInt(bonusNum) < MINIMUM_CANDIDATE_NUMBER ||
                Integer.parseInt(bonusNum) > MAXIMUM_CANDIDATE_NUMBER) {
            throw new IllegalArgumentException("1~45사이의 숫자를 입력해주세요");
        }
        if (numbers.contains(Integer.parseInt(bonusNum))) {
            throw new IllegalArgumentException("당첨 번호와 중복일 수 없습니다.");
        }
    }
}
