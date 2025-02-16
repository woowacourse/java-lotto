package View;

import Constant.Constants;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InputView {

    private static final String DELIMITER = ",";

    public static int inputPrice(String inputPrice) {
        return validateInteger(inputPrice);
    }

    public static List<Integer> inputWinnerNumbers(String inputWinnerNumbers) {
        validateEmptyInput(inputWinnerNumbers);

        String[] splitInput = inputWinnerNumbers.split(DELIMITER);

        List<Integer> winnerNumbers = parseWinnerNumbers(splitInput);
        validateDuplicateValue(winnerNumbers);
        return winnerNumbers;
    }

    private static List<Integer> parseWinnerNumbers(String[] splitInput) {
        List<Integer> winnerNumbers = new ArrayList<>();
        for (String s : splitInput) {
            int number = validateInteger(s);
            validateNumberRange(number);
            winnerNumbers.add(number);
        }
        return winnerNumbers;
    }

    public static int inputBonusBall(String inputBonusBall, List<Integer> winnerNumbers) {

        validateEmptyInput(inputBonusBall);

        int bonusBall = validateInteger(inputBonusBall);

        validateNumberRange(bonusBall);
        validateDuplicateBonusBall(winnerNumbers, bonusBall);
        return bonusBall;
    }

    private static void validateDuplicateBonusBall(List<Integer> winnerNumbers, int bonusBall) {
        if (winnerNumbers.contains(bonusBall)) {
            throw new IllegalArgumentException("중복된 값을 입력해서는 안됩니다.");
        }
    }

    private static void validateEmptyInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("유효하지않는 입력입니다.");
        }
    }

    private static void validateNumberRange(int value) {
        if (value < Constants.LOTTO_MIN_NUMBER || value > Constants.LOTTO_MAX_NUMBER) {
            throw new IllegalArgumentException("1에서 45 사이의 정수를 입력해주세요.");
        }
    }

    private static void validateDuplicateValue(List<Integer> winnerNumbers) {
        Set<Integer> duplicateCheck = new HashSet<>(winnerNumbers);
        if (duplicateCheck.size() != winnerNumbers.size()) {
            throw new IllegalArgumentException("중복된 값을 입력해서는 안됩니다.");
        }

    }

    private static int validateInteger(String input) {
        try {
            return Integer.parseInt(input);

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력 가능합니다.", e);
        }
    }
}
