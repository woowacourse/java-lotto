package view.inputview;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import validator.LottoNumberValidator;

public class WinningNumberInputView extends ConsoleInputView<List<Integer>> {
    private static final String INPUT_WINNING_NUMBER_MESSAGE = "지난 주 당첨 번호를 입럭해 주세요.";
    private static final String NOT_INTEGER_ERROR_MESSAGE = "입력된 값이 정수가 아닙니다.";

    @Override
    public List<Integer> getUserInputData() {
        System.out.println(INPUT_WINNING_NUMBER_MESSAGE);
        try {
            return inputWinningNumber();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return getUserInputData();
        }
    }

    private List<Integer> inputWinningNumber() {
        String inputData = inputLine();
        List<String> splitInputData = splitInputData(inputData);
        splitInputData.forEach(splitNumber -> LottoNumberValidator.validate(parse(splitNumber)));
        return splitInputData.stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    private List<String> splitInputData(String inputData) {
        return Arrays.stream(inputData.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private static int parse(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_INTEGER_ERROR_MESSAGE);
        }
    }
}
