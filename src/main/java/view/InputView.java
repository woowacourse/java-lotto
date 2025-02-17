package view;

import constant.LottoConfig;
import java.util.ArrayList;
import java.util.List;
import model.vo.LottoNumber;

public class InputView {

    private static final String DELIMITER = ",";

    public static int inputPrice(String inputPrice) {
        return validateInteger(inputPrice);
    }

    public static List<Integer> inputWinnerNumbers(String inputWinnerNumbers) {
        validateEmptyInput(inputWinnerNumbers);
        String[] splitInput = inputWinnerNumbers.split(DELIMITER);
        return parseWinnerNumbers(splitInput);
    }

    private static List<Integer> parseWinnerNumbers(String[] splitInput) {
        List<Integer> winnerNumbers = new ArrayList<>();
        for (String s : splitInput) {
            int number = validateInteger(s);
            winnerNumbers.add(new LottoNumber(number).getNumber());
        }
        return winnerNumbers;
    }

    public static int inputBonusBall(String inputBonusBall, List<Integer> winnerNumbers) {
        validateEmptyInput(inputBonusBall);
        int bonusBall = validateInteger(inputBonusBall);
        return new LottoNumber(bonusBall).getNumber();
    }

    private static void validateEmptyInput(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("유효하지않는 입력입니다.");
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
