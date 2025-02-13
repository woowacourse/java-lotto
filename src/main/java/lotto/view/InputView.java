package lotto.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);
    public int inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        int validatedMoney = validateNumber(scanner.nextLine(), "구입금액은 숫자여야 합니다.");
        validateUnit(validatedMoney);
        validateNegative(validatedMoney);
        validateLimit(validatedMoney);
        return validatedMoney;
    }

    public List<Integer> inputWinningLotto() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        String winningLottoInput = scanner.nextLine();
        List<String> splitedLotto = validateLength(winningLottoInput);
        List<Integer> parsedLotto = validateIsNumber(splitedLotto);
        validateRange(parsedLotto);
        validateDuplicate(parsedLotto);
        return parsedLotto;
    }

    public int inputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
        String bonusNumberInput = scanner.nextLine();
        int parsedBonusNumber = validateNumber(bonusNumberInput, "보너스 숫자는 숫자여야 합니다.");
        checkRange(parsedBonusNumber);
        return parsedBonusNumber;
    }

    private void validateDuplicate(List<Integer> parsedLotto) {
        Set<Integer> unDuplicatedLotto = new HashSet<>(parsedLotto);
        if (unDuplicatedLotto.size() != 6) {
            throw new IllegalArgumentException("로또 6개의 숫자를 입력하셔야 합니다.");
        }
    }

    private void validateRange(List<Integer> parsedLotto) {
        for (Integer number : parsedLotto) {
            checkRange(number);
        }
    }

    private void checkRange(int number) {
        if (number <= 0 || number > 45) {
            throw new IllegalArgumentException("1과 45 사이의 수를 입력하세요.");
        }
    }

    private List<Integer> validateIsNumber(List<String> splitedLotto) {
        List<Integer> parsedLotto = new ArrayList<>();
        for (String lottoNumber : splitedLotto) {
            parsedLotto.add(validateNumber(lottoNumber, "당첨 번호는 숫자를 입력해야 합니다."));
        }
        return parsedLotto;
    }

    private List<String> validateLength(String winningLottoInput) {
        List<String> winningNumbers = List.of(winningLottoInput
                .replaceAll(" ", "")
                .split(","));
        if (winningNumbers.size() != 6) {
            throw new IllegalArgumentException("6자리를 입력하세요.");
        }
        return winningNumbers;
    }

    private int validateNumber(String money, String message) {
        try {
            return Integer.parseInt(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(message);
        }
    }
    private void validateUnit(int validatedMoney) {
        if (validatedMoney % 1000 != 0) {
            throw new IllegalArgumentException("구입금액은 천원 단위여야 합니다.");
        }
    }
    private void validateNegative(int validatedMoney) {
        if (validatedMoney <= 0) {
            throw new IllegalArgumentException("구입금액은 천원부터입니다");
        }
    }
    private void validateLimit(int validatedMoney) {
        if (validatedMoney > 100000) {
            throw new IllegalArgumentException("10만원까지 구매 가능합니다.");
        }
    }
}
